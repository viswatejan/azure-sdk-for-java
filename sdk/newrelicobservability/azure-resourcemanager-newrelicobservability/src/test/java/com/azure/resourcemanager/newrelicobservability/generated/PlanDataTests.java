// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.newrelicobservability.generated;

import com.azure.core.util.BinaryData;
import com.azure.resourcemanager.newrelicobservability.models.BillingCycle;
import com.azure.resourcemanager.newrelicobservability.models.PlanData;
import com.azure.resourcemanager.newrelicobservability.models.UsageType;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Assertions;

public final class PlanDataTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        PlanData model =
            BinaryData
                .fromString(
                    "{\"usageType\":\"PAYG\",\"billingCycle\":\"MONTHLY\",\"planDetails\":\"mdwzjeiachboo\",\"effectiveDate\":\"2021-07-31T08:54:57Z\"}")
                .toObject(PlanData.class);
        Assertions.assertEquals(UsageType.PAYG, model.usageType());
        Assertions.assertEquals(BillingCycle.MONTHLY, model.billingCycle());
        Assertions.assertEquals("mdwzjeiachboo", model.planDetails());
        Assertions.assertEquals(OffsetDateTime.parse("2021-07-31T08:54:57Z"), model.effectiveDate());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        PlanData model =
            new PlanData()
                .withUsageType(UsageType.PAYG)
                .withBillingCycle(BillingCycle.MONTHLY)
                .withPlanDetails("mdwzjeiachboo")
                .withEffectiveDate(OffsetDateTime.parse("2021-07-31T08:54:57Z"));
        model = BinaryData.fromObject(model).toObject(PlanData.class);
        Assertions.assertEquals(UsageType.PAYG, model.usageType());
        Assertions.assertEquals(BillingCycle.MONTHLY, model.billingCycle());
        Assertions.assertEquals("mdwzjeiachboo", model.planDetails());
        Assertions.assertEquals(OffsetDateTime.parse("2021-07-31T08:54:57Z"), model.effectiveDate());
    }
}
