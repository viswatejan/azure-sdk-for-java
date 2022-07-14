// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.core.http.apache.implementation;

import org.apache.hc.core5.annotation.Contract;
import org.apache.hc.core5.annotation.ThreadingBehavior;
import org.apache.hc.core5.http.HttpStreamResetException;
import org.apache.hc.core5.http.nio.AsyncDataProducer;
import org.apache.hc.core5.http.nio.DataStreamChannel;
import org.apache.hc.core5.util.Args;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * TODO:
 * Without sync lock on buffer
 */
@Contract(threading = ThreadingBehavior.SAFE)
final class ApacheReactiveDataProducer implements AsyncDataProducer, Subscriber<ByteBuffer> {

    private static final int BUFFER_WINDOW_SIZE = 5;

    private final AtomicReference<DataStreamChannel> requestChannel = new AtomicReference<>();
    private final AtomicReference<Throwable> exception = new AtomicReference<>();
    private final AtomicBoolean complete = new AtomicBoolean(false);
    private final Publisher<ByteBuffer> publisher;
    private final AtomicReference<Subscription> subscription = new AtomicReference<>();
    private final ArrayDeque<ByteBuffer> buffers = new ArrayDeque<>(); // This field requires synchronization

    public ApacheReactiveDataProducer(final Publisher<ByteBuffer> publisher) {
        this.publisher = Args.notNull(publisher, "publisher");
    }

    void setChannel(final DataStreamChannel channel) {
        requestChannel.set(channel);
    }

    @Override
    public void onSubscribe(final Subscription subscription) {
        if (this.subscription.getAndSet(subscription) != null) {
            throw new IllegalStateException("Already subscribed");
        }

        subscription.request(BUFFER_WINDOW_SIZE);
    }

    @Override
    public void onNext(final ByteBuffer byteBuffer) {
        buffers.add(byteBuffer);
        signalReadiness();
    }

    @Override
    public void onError(final Throwable throwable) {
        subscription.set(null);
        exception.set(throwable);
        signalReadiness();
    }

    @Override
    public void onComplete() {
        subscription.set(null);
        complete.set(true);
        signalReadiness();
    }

    private void signalReadiness() {
        final DataStreamChannel channel = requestChannel.get();
        if (channel == null) {
            throw new IllegalStateException("Output channel is not set");
        }
        channel.requestOutput();
    }

    @Override
    public int available() {
        if (exception.get() != null || complete.get()) {
            return 1;
        } else {
            synchronized (buffers) {
                int sum = 0;
                for (final ByteBuffer buffer : buffers) {
                    sum += buffer.remaining();
                }
                return sum;
            }
        }
    }

    @Override
    public void produce(final DataStreamChannel channel) throws IOException {
        if (requestChannel.get() == null) {
            requestChannel.set(channel);
            publisher.subscribe(this);
        }

        final Throwable t = exception.get();
        final Subscription s = subscription.get();
        int buffersToReplenish = 0;
        try {
            synchronized (buffers) {
                if (t != null) {
                    throw new HttpStreamResetException(t.getMessage(), t);
                } else if (this.complete.get() && buffers.isEmpty()) {
                    channel.endStream();
                } else {
                    while (!buffers.isEmpty()) {
                        final ByteBuffer nextBuffer = buffers.remove();
                        channel.write(nextBuffer);
                        if (nextBuffer.remaining() > 0) {
                            buffers.push(nextBuffer);
                            break;
                        } else if (s != null) {
                            // We defer the #request call until after we release the buffer lock.
                            buffersToReplenish++;
                        }
                    }
                }
            }
        } finally {
            if (s != null && buffersToReplenish > 0) {
                s.request(buffersToReplenish);
            }
        }
    }

    @Override
    public void releaseResources() {
        final Subscription s = subscription.getAndSet(null);
        if (s != null) {
            s.cancel();
        }
    }
}
