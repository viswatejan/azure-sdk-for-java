// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.appservice.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The KeyValuePairStringObject model. */
@Immutable
public final class KeyValuePairStringObject {
    /*
     * The key property.
     */
    @JsonProperty(value = "key", access = JsonProperty.Access.WRITE_ONLY)
    private String key;

    /*
     * Any object
     */
    @JsonProperty(value = "value", access = JsonProperty.Access.WRITE_ONLY)
    private Object value;

    /** Creates an instance of KeyValuePairStringObject class. */
    public KeyValuePairStringObject() {
    }

    /**
     * Get the key property: The key property.
     *
     * @return the key value.
     */
    public String key() {
        return this.key;
    }

    /**
     * Get the value property: Any object.
     *
     * @return the value value.
     */
    public Object value() {
        return this.value;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
