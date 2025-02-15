// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.webpubsub.generated;

/** Samples for WebPubSubReplicas List. */
public final class WebPubSubReplicasListSamples {
    /*
     * x-ms-original-file: specification/webpubsub/resource-manager/Microsoft.SignalRService/preview/2023-06-01-preview/examples/WebPubSubReplicas_List.json
     */
    /**
     * Sample code: WebPubSubReplicas_List.
     *
     * @param manager Entry point to WebPubSubManager.
     */
    public static void webPubSubReplicasList(com.azure.resourcemanager.webpubsub.WebPubSubManager manager) {
        manager.webPubSubReplicas().list("myResourceGroup", "myWebPubSubService", com.azure.core.util.Context.NONE);
    }
}
