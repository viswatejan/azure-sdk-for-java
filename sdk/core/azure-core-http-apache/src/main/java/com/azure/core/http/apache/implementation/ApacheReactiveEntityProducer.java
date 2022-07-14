package com.azure.core.http.apache.implementation;

import org.apache.hc.core5.annotation.Contract;
import org.apache.hc.core5.annotation.ThreadingBehavior;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.nio.AsyncEntityProducer;
import org.apache.hc.core5.http.nio.DataStreamChannel;
import org.reactivestreams.Publisher;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Set;

/**
 * TODO:
 * Without sync lock on buffer
 */
@Contract(threading = ThreadingBehavior.SAFE)
public final class ApacheReactiveEntityProducer implements AsyncEntityProducer {

    private final ApacheReactiveDataProducer reactiveDataProducer;

    private final long contentLength;
    private final ContentType contentType;
    private final String contentEncoding;

    /**
     * Creates a new {@code ReactiveEntityProducer} with the given parameters.
     *
     * @param publisher the publisher of the entity stream.
     * @param contentLength the length of the entity, or -1 if unknown (implies chunked encoding).
     * @param contentType the {@code Content-Type} of the entity, or null if none.
     * @param contentEncoding the {@code Content-Encoding} of the entity, or null if none.
     */
    public ApacheReactiveEntityProducer(
        final Publisher<ByteBuffer> publisher,
        final long contentLength,
        final ContentType contentType,
        final String contentEncoding
    ) {
        this.reactiveDataProducer = new ApacheReactiveDataProducer(publisher);
        this.contentLength = contentLength;
        this.contentType = contentType;
        this.contentEncoding = contentEncoding;
    }

    @Override
    public int available() {
        return reactiveDataProducer.available();
    }

    @Override
    public void produce(final DataStreamChannel channel) throws IOException {
        reactiveDataProducer.produce(channel);
    }

    @Override
    public void releaseResources() {
        reactiveDataProducer.releaseResources();
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public void failed(final Exception cause) {
        releaseResources();
    }

    @Override
    public long getContentLength() {
        return contentLength;
    }

    @Override
    public String getContentType() {
        return contentType != null ? contentType.toString() : null;
    }

    @Override
    public String getContentEncoding() {
        return contentEncoding;
    }

    @Override
    public boolean isChunked() {
        return contentLength == -1;
    }

    @Override
    public Set<String> getTrailerNames() {
        return null;
    }
}
