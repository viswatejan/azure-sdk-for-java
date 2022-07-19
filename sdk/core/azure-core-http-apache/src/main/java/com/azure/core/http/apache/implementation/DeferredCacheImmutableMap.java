// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.core.http.apache.implementation;

import com.azure.core.util.logging.ClientLogger;
import org.apache.hc.core5.http.HttpMessage;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class represents an immutable map used by {@link ApacheToAzureCoreHttpHeadersWrapper} which has an internal
 * deferred cache.
 *
 * @param <V> Type of the value.
 */
final class DeferredCacheImmutableMap<V> extends AbstractMap<String, V> {
    private final ClientLogger logger;
    private final Map<String, V> internalCache;
    private final HttpMessage apacheHeaders;
    private final Function<List<String>, V> getter;

    DeferredCacheImmutableMap(ClientLogger logger, Map<String, V> internalCache, HttpMessage apacheHeaders,
                              Function<List<String>, V> getter) {
        this.logger = logger;
        this.internalCache = internalCache;
        this.apacheHeaders = apacheHeaders;
        this.getter = getter;
    }

    @Override
    public int size() {
        return apacheHeaders.getHeaders().length;
    }

    @Override
    public boolean isEmpty() {
        return apacheHeaders.getHeaders().length == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return apacheHeaders.getHeaders((String) key).length != 0;
    }

    @Override
    public boolean containsValue(Object value) {
        throw logger.logExceptionAsWarning(new UnsupportedOperationException());
    }

    @Override
    public V get(final Object key) {

        // Apache headers are stored as linked list. apacheHeaders.getHeaders(k) returns all Headers object that has
        // header name k, collect all values and return as a List of String.
        return internalCache.computeIfAbsent((String) key, k -> getter.apply(
            Arrays.stream(apacheHeaders.getHeaders(k))
                .map(header -> header.getValue())
                .collect(Collectors.toList()))
        );
    }

    @Override
    public V put(String key, V value) {
        throw logger.logExceptionAsWarning(new UnsupportedOperationException());
    }

    @Override
    public V remove(Object key) {
        throw logger.logExceptionAsWarning(new UnsupportedOperationException());
    }

    @Override
    public void putAll(Map<? extends String, ? extends V> m) {
        throw logger.logExceptionAsWarning(new UnsupportedOperationException());
    }

    @Override
    public void clear() {
        throw logger.logExceptionAsWarning(new UnsupportedOperationException());
    }

    @Override
    public Set<Entry<String, V>> entrySet() {
        return new AbstractSet<Entry<String, V>>() {
            @Override
            public Iterator<Entry<String, V>> iterator() {
                return Arrays.stream(apacheHeaders.getHeaders())
                    .map(header -> {
                        String headerName = header.getName();
                        return (Entry<String, V>) new SimpleImmutableEntry<>(headerName, get(headerName));
                    }).iterator();
            }

            @Override
            public int size() {
                return DeferredCacheImmutableMap.this.size();
            }
        };
    }
}
