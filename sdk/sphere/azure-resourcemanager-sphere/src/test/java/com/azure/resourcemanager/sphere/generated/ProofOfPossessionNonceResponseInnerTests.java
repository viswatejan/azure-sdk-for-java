// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.sphere.generated;

import com.azure.core.util.BinaryData;
import com.azure.resourcemanager.sphere.fluent.models.ProofOfPossessionNonceResponseInner;

public final class ProofOfPossessionNonceResponseInnerTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        ProofOfPossessionNonceResponseInner model =
            BinaryData
                .fromString(
                    "{\"certificate\":\"xbnjbiksq\",\"status\":\"Inactive\",\"subject\":\"sainqpjwnzl\",\"thumbprint\":\"fmppe\",\"expiryUtc\":\"2021-03-28T22:28:31Z\",\"notBeforeUtc\":\"2021-11-23T16:03:48Z\",\"provisioningState\":\"Provisioning\"}")
                .toObject(ProofOfPossessionNonceResponseInner.class);
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        ProofOfPossessionNonceResponseInner model = new ProofOfPossessionNonceResponseInner();
        model = BinaryData.fromObject(model).toObject(ProofOfPossessionNonceResponseInner.class);
    }
}
