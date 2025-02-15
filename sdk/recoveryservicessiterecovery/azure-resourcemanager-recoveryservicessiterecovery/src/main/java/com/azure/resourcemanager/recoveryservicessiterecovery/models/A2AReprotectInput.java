// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.recoveryservicessiterecovery.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;

/** Azure specific reprotect input. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "instanceType")
@JsonTypeName("A2A")
@Fluent
public final class A2AReprotectInput extends ReverseReplicationProviderSpecificInput {
    /*
     * The recovery container Id.
     */
    @JsonProperty(value = "recoveryContainerId")
    private String recoveryContainerId;

    /*
     * The list of vm disk details.
     */
    @JsonProperty(value = "vmDisks")
    private List<A2AVmDiskInputDetails> vmDisks;

    /*
     * The recovery resource group Id. Valid for V2 scenarios.
     */
    @JsonProperty(value = "recoveryResourceGroupId")
    private String recoveryResourceGroupId;

    /*
     * The recovery cloud service Id. Valid for V1 scenarios.
     */
    @JsonProperty(value = "recoveryCloudServiceId")
    private String recoveryCloudServiceId;

    /*
     * The recovery availability set.
     */
    @JsonProperty(value = "recoveryAvailabilitySetId")
    private String recoveryAvailabilitySetId;

    /*
     * The Policy Id.
     */
    @JsonProperty(value = "policyId")
    private String policyId;

    /** Creates an instance of A2AReprotectInput class. */
    public A2AReprotectInput() {
    }

    /**
     * Get the recoveryContainerId property: The recovery container Id.
     *
     * @return the recoveryContainerId value.
     */
    public String recoveryContainerId() {
        return this.recoveryContainerId;
    }

    /**
     * Set the recoveryContainerId property: The recovery container Id.
     *
     * @param recoveryContainerId the recoveryContainerId value to set.
     * @return the A2AReprotectInput object itself.
     */
    public A2AReprotectInput withRecoveryContainerId(String recoveryContainerId) {
        this.recoveryContainerId = recoveryContainerId;
        return this;
    }

    /**
     * Get the vmDisks property: The list of vm disk details.
     *
     * @return the vmDisks value.
     */
    public List<A2AVmDiskInputDetails> vmDisks() {
        return this.vmDisks;
    }

    /**
     * Set the vmDisks property: The list of vm disk details.
     *
     * @param vmDisks the vmDisks value to set.
     * @return the A2AReprotectInput object itself.
     */
    public A2AReprotectInput withVmDisks(List<A2AVmDiskInputDetails> vmDisks) {
        this.vmDisks = vmDisks;
        return this;
    }

    /**
     * Get the recoveryResourceGroupId property: The recovery resource group Id. Valid for V2 scenarios.
     *
     * @return the recoveryResourceGroupId value.
     */
    public String recoveryResourceGroupId() {
        return this.recoveryResourceGroupId;
    }

    /**
     * Set the recoveryResourceGroupId property: The recovery resource group Id. Valid for V2 scenarios.
     *
     * @param recoveryResourceGroupId the recoveryResourceGroupId value to set.
     * @return the A2AReprotectInput object itself.
     */
    public A2AReprotectInput withRecoveryResourceGroupId(String recoveryResourceGroupId) {
        this.recoveryResourceGroupId = recoveryResourceGroupId;
        return this;
    }

    /**
     * Get the recoveryCloudServiceId property: The recovery cloud service Id. Valid for V1 scenarios.
     *
     * @return the recoveryCloudServiceId value.
     */
    public String recoveryCloudServiceId() {
        return this.recoveryCloudServiceId;
    }

    /**
     * Set the recoveryCloudServiceId property: The recovery cloud service Id. Valid for V1 scenarios.
     *
     * @param recoveryCloudServiceId the recoveryCloudServiceId value to set.
     * @return the A2AReprotectInput object itself.
     */
    public A2AReprotectInput withRecoveryCloudServiceId(String recoveryCloudServiceId) {
        this.recoveryCloudServiceId = recoveryCloudServiceId;
        return this;
    }

    /**
     * Get the recoveryAvailabilitySetId property: The recovery availability set.
     *
     * @return the recoveryAvailabilitySetId value.
     */
    public String recoveryAvailabilitySetId() {
        return this.recoveryAvailabilitySetId;
    }

    /**
     * Set the recoveryAvailabilitySetId property: The recovery availability set.
     *
     * @param recoveryAvailabilitySetId the recoveryAvailabilitySetId value to set.
     * @return the A2AReprotectInput object itself.
     */
    public A2AReprotectInput withRecoveryAvailabilitySetId(String recoveryAvailabilitySetId) {
        this.recoveryAvailabilitySetId = recoveryAvailabilitySetId;
        return this;
    }

    /**
     * Get the policyId property: The Policy Id.
     *
     * @return the policyId value.
     */
    public String policyId() {
        return this.policyId;
    }

    /**
     * Set the policyId property: The Policy Id.
     *
     * @param policyId the policyId value to set.
     * @return the A2AReprotectInput object itself.
     */
    public A2AReprotectInput withPolicyId(String policyId) {
        this.policyId = policyId;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
        if (vmDisks() != null) {
            vmDisks().forEach(e -> e.validate());
        }
    }
}
