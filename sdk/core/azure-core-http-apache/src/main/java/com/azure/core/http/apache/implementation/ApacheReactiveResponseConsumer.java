package com.azure.core.http.apache.implementation;

import org.apache.hc.core5.annotation.Contract;
import org.apache.hc.core5.annotation.ThreadingBehavior;
import org.apache.hc.core5.concurrent.BasicFuture;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.Message;
import org.apache.hc.core5.http.nio.AsyncResponseConsumer;
import org.apache.hc.core5.http.nio.CapacityChannel;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.Args;
import org.reactivestreams.Publisher;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 *
 */
@Contract(threading = ThreadingBehavior.SAFE)
public final class ApacheReactiveResponseConsumer implements AsyncResponseConsumer<Void> {

    private final ApacheReactiveDataConsumer reactiveDataConsumer = new ApacheReactiveDataConsumer();
    private final List<Header> trailers = Collections.synchronizedList(new ArrayList<Header>());
    private final BasicFuture<Message<HttpResponse, Publisher<ByteBuffer>>> responseFuture;

    private volatile BasicFuture<Void> responseCompletion;
    private volatile HttpResponse informationResponse;
    private volatile EntityDetails entityDetails;

    /**
     * Creates a {@code ReactiveResponseConsumer}.
     */
    public ApacheReactiveResponseConsumer() {
        this.responseFuture = new BasicFuture<>(null);
    }

    /**
     * Creates a {@code ReactiveResponseConsumer} that will call back the supplied {@link FutureCallback} with a
     * streamable response.
     *
     * @param responseCallback the callback to invoke when the response is available for consumption.
     */
    public ApacheReactiveResponseConsumer(final FutureCallback<Message<HttpResponse, Publisher<ByteBuffer>>> responseCallback) {
        this.responseFuture = new BasicFuture<>(Args.notNull(responseCallback, "responseCallback"));
    }

    public Future<Message<HttpResponse, Publisher<ByteBuffer>>> getResponseFuture() {
        return responseFuture;
    }

    /**
     * Returns the intermediate (1xx) HTTP response if one was received.
     *
     * @return the information response, or {@code null} if none.
     */
    public HttpResponse getInformationResponse() {
        return informationResponse;
    }

    /**
     * Returns the response entity details.
     *
     * @return the entity details, or {@code null} if none.
     */
    public EntityDetails getEntityDetails() {
        return entityDetails;
    }

    /**
     * Returns the trailers received at the end of the response.
     *
     * @return a non-null list of zero or more trailers.
     */
    public List<Header> getTrailers() {
        return trailers;
    }

    @Override
    public void consumeResponse(
        final HttpResponse response,
        final EntityDetails entityDetails,
        final HttpContext httpContext,
        final FutureCallback<Void> resultCallback
    ) {
        this.entityDetails = entityDetails;
        this.responseCompletion = new BasicFuture<>(resultCallback);
        this.responseFuture.completed(new Message<HttpResponse, Publisher<ByteBuffer>>(response, reactiveDataConsumer));
        if (entityDetails == null) {
            streamEnd(null);
        }
    }

    @Override
    public void informationResponse(final HttpResponse response, final HttpContext httpContext) {
        this.informationResponse = response;
    }

    @Override
    public void failed(final Exception cause) {
        reactiveDataConsumer.failed(cause);
        responseFuture.failed(cause);
        if (responseCompletion != null) {
            responseCompletion.failed(cause);
        }
    }

    @Override
    public void updateCapacity(final CapacityChannel capacityChannel) throws IOException {
        reactiveDataConsumer.updateCapacity(capacityChannel);
    }

    @Override
    public void consume(final ByteBuffer src) throws IOException {
        reactiveDataConsumer.consume(src);
    }

    @Override
    public void streamEnd(final List<? extends Header> trailers) {
        if (trailers != null) {
            this.trailers.addAll(trailers);
        }
        reactiveDataConsumer.streamEnd(trailers);
        responseCompletion.completed(null);
    }

    @Override
    public void releaseResources() {
        reactiveDataConsumer.releaseResources();
        responseFuture.cancel();
        if (responseCompletion != null) {
            responseCompletion.cancel();
        }
    }
}
