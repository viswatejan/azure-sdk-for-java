// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.dataprotection.implementation;

import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.Context;
import com.azure.core.util.logging.ClientLogger;
import com.azure.resourcemanager.dataprotection.fluent.DataProtectionOperationsClient;
import com.azure.resourcemanager.dataprotection.fluent.models.ClientDiscoveryValueForSingleApiInner;
import com.azure.resourcemanager.dataprotection.models.ClientDiscoveryValueForSingleApi;
import com.azure.resourcemanager.dataprotection.models.DataProtectionOperations;

public final class DataProtectionOperationsImpl implements DataProtectionOperations {
    private static final ClientLogger LOGGER = new ClientLogger(DataProtectionOperationsImpl.class);

    private final DataProtectionOperationsClient innerClient;

    private final com.azure.resourcemanager.dataprotection.DataProtectionManager serviceManager;

    public DataProtectionOperationsImpl(
        DataProtectionOperationsClient innerClient,
        com.azure.resourcemanager.dataprotection.DataProtectionManager serviceManager) {
        this.innerClient = innerClient;
        this.serviceManager = serviceManager;
    }

    public PagedIterable<ClientDiscoveryValueForSingleApi> list() {
        PagedIterable<ClientDiscoveryValueForSingleApiInner> inner = this.serviceClient().list();
        return Utils.mapPage(inner, inner1 -> new ClientDiscoveryValueForSingleApiImpl(inner1, this.manager()));
    }

    public PagedIterable<ClientDiscoveryValueForSingleApi> list(Context context) {
        PagedIterable<ClientDiscoveryValueForSingleApiInner> inner = this.serviceClient().list(context);
        return Utils.mapPage(inner, inner1 -> new ClientDiscoveryValueForSingleApiImpl(inner1, this.manager()));
    }

    private DataProtectionOperationsClient serviceClient() {
        return this.innerClient;
    }

    private com.azure.resourcemanager.dataprotection.DataProtectionManager manager() {
        return this.serviceManager;
    }
}
