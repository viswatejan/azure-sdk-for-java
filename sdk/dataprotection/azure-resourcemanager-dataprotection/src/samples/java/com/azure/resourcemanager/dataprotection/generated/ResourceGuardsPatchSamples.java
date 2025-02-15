// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.dataprotection.generated;

import com.azure.resourcemanager.dataprotection.models.ResourceGuardResource;
import java.util.HashMap;
import java.util.Map;

/** Samples for ResourceGuards Patch. */
public final class ResourceGuardsPatchSamples {
    /*
     * x-ms-original-file: specification/dataprotection/resource-manager/Microsoft.DataProtection/stable/2023-05-01/examples/ResourceGuardCRUD/PatchResourceGuard.json
     */
    /**
     * Sample code: Patch ResourceGuard.
     *
     * @param manager Entry point to DataProtectionManager.
     */
    public static void patchResourceGuard(com.azure.resourcemanager.dataprotection.DataProtectionManager manager) {
        ResourceGuardResource resource =
            manager
                .resourceGuards()
                .getByResourceGroupWithResponse(
                    "SampleResourceGroup", "swaggerExample", com.azure.core.util.Context.NONE)
                .getValue();
        resource.update().withTags(mapOf("newKey", "fakeTokenPlaceholder")).apply();
    }

    @SuppressWarnings("unchecked")
    private static <T> Map<String, T> mapOf(Object... inputs) {
        Map<String, T> map = new HashMap<>();
        for (int i = 0; i < inputs.length; i += 2) {
            String key = (String) inputs[i];
            T value = (T) inputs[i + 1];
            map.put(key, value);
        }
        return map;
    }
}
