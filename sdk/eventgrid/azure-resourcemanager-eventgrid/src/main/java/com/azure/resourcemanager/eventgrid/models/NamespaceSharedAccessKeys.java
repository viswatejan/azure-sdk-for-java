// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.eventgrid.models;

import com.azure.resourcemanager.eventgrid.fluent.models.NamespaceSharedAccessKeysInner;

/** An immutable client-side representation of NamespaceSharedAccessKeys. */
public interface NamespaceSharedAccessKeys {
    /**
     * Gets the key1 property: Shared access key1 for the namespace.
     *
     * @return the key1 value.
     */
    String key1();

    /**
     * Gets the key2 property: Shared access key2 for the namespace.
     *
     * @return the key2 value.
     */
    String key2();

    /**
     * Gets the inner com.azure.resourcemanager.eventgrid.fluent.models.NamespaceSharedAccessKeysInner object.
     *
     * @return the inner object.
     */
    NamespaceSharedAccessKeysInner innerModel();
}
