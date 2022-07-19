// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.core.http.apache.implementation;

import com.azure.core.http.HttpHeader;
import com.azure.core.http.HttpHeaders;
import com.azure.core.util.logging.ClientLogger;
import org.apache.hc.core5.http.HttpMessage;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// This class wraps an Apache HttpHeaders instance and provides an azure-core HttpHeaders view onto it.
// This avoids the need to copy the Apache HttpHeaders into an azure-core HttpHeaders instance.
// Whilst it is not necessary to support mutability (as these headers are the result of an Apache response), we do so in
// any case, given the additional implementation cost is minimal.
public class ApacheToAzureCoreHttpHeadersWrapper extends HttpHeaders {
    // This wrapper is frequently created, so we are OK with creating a single shared logger instance here,
    // to lessen the cost of this type.
    private static final ClientLogger LOGGER = new ClientLogger(ApacheToAzureCoreHttpHeadersWrapper.class);

    // The Apache HttpHeaders we are wrapping
    private final HttpMessage apacheHeaders;

    // this is an AbstractMap that we create to virtualize a view onto the Netty HttpHeaders type, for use in the
    // toMap API. We lazily instantiate it when toMap is called, and then reuse that for all future calls.
    private Map<String, String> abstractMap;

    // this is an AbstractMap that we create to virtualize a view onto the Netty HttpHeaders type, for use in the
    // toMultiMap API. We lazily instantiate it when toMap is called, and then reuse that for all future calls.
    private Map<String, String[]> abstractMultiMap;

    public ApacheToAzureCoreHttpHeadersWrapper(HttpMessage apacheHeaders) {
        this.apacheHeaders = apacheHeaders;
    }

    @Override
    public int getSize() {
        return apacheHeaders.getHeaders().length;
    }

    @Override
    public HttpHeaders set(String name, String value) {
        if (name == null) {
            return this;
        }

        if (value == null) {
            // our general contract in HttpHeaders is that a null value will result in any key with this name
            // being removed.
            remove(name);
        } else {
            apacheHeaders.setHeader(name, value);
        }
        return this;
    }

    @Override
    public HttpHeaders set(String name, List<String> values) {
        if (name == null) {
            return this;
        }

        if (values == null) {
            // our general contract in HttpHeaders is that a null value will result in any key with this name
            // being removed.
            remove(name);
        } else {
            apacheHeaders.setHeader(name, values);
        }
        return this;
    }

    public HttpHeaders add(String name, String value) {
        if (name == null) {
            return this;
        }

        if (value == null) {
            // our general contract in HttpHeaders is that a null value will result in any key with this name
            // being removed.
            remove(name);
        } else {
            apacheHeaders.addHeader(name, value);
        }
        return this;
    }

    @Override
    public HttpHeader get(String name) {
        if (apacheHeaders.containsHeader(name)) {

            return new ApacheHttpHeader(this, name,
                Arrays.stream(apacheHeaders.getHeaders(name))
                    .map(header ->  header.getValue())
                    .collect(Collectors.toList()));
        }
        return null;
    }

    @Override
    public HttpHeader remove(String name) {
        HttpHeader header = get(name);
        // remove all Headers that has this name
        apacheHeaders.removeHeaders(name);
        return header;
    }

    @Override
    public String getValue(String name) {
        final HttpHeader header = get(name);
        return (header == null) ? null : header.getValue();
    }

    @Override
    public String[] getValues(String name) {
        final HttpHeader header = get(name);
        return (header == null) ? null : header.getValues();
    }

    @Override
    public Map<String, String> toMap() {
        if (abstractMap == null) {
            abstractMap = new DeferredCacheImmutableMap<>(LOGGER, new HashMap<>(), apacheHeaders,
                getAll -> String.join(",", getAll));
        }
        return abstractMap;
    }

    Map<String, String[]> toMultiMap() {
        if (abstractMultiMap == null) {
            abstractMultiMap = new DeferredCacheImmutableMap<>(LOGGER, new HashMap<>(), apacheHeaders,
                getAll -> getAll.toArray(new String[0]));
        }
        return abstractMultiMap;
    }

    @Override
    public Iterator<HttpHeader> iterator() {
        return stream().iterator();
    }

    @Override
    public Stream<HttpHeader> stream() {
        return Arrays.stream(apacheHeaders.getHeaders())
            .map(header -> new ApacheHttpHeader(this, header.getName(), header.getValue()));
    }

    static class ApacheHttpHeader extends HttpHeader {
        private final ApacheToAzureCoreHttpHeadersWrapper allHeaders;

        ApacheHttpHeader(ApacheToAzureCoreHttpHeadersWrapper allHeaders, String name, String value) {
            super(name, value);
            this.allHeaders = allHeaders;
        }

        ApacheHttpHeader(ApacheToAzureCoreHttpHeadersWrapper allHeaders, String name, List<String> values) {
            super(name, values);
            this.allHeaders = allHeaders;
        }

        @Override
        public void addValue(String value) {
            super.addValue(value);
            allHeaders.add(getName(), value);
        }
    }
}
