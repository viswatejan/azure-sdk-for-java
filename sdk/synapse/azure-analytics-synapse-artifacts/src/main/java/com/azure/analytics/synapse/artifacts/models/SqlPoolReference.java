// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.analytics.synapse.artifacts.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** SQL pool reference type. */
@Fluent
public final class SqlPoolReference {
    /*
     * SQL pool reference type.
     */
    @JsonProperty(value = "type", required = true)
    private SqlPoolReferenceType type;

    /*
     * Reference SQL pool name.
     */
    @JsonProperty(value = "referenceName", required = true)
    private String referenceName;

    /** Creates an instance of SqlPoolReference class. */
    public SqlPoolReference() {}

    /**
     * Get the type property: SQL pool reference type.
     *
     * @return the type value.
     */
    public SqlPoolReferenceType getType() {
        return this.type;
    }

    /**
     * Set the type property: SQL pool reference type.
     *
     * @param type the type value to set.
     * @return the SqlPoolReference object itself.
     */
    public SqlPoolReference setType(SqlPoolReferenceType type) {
        this.type = type;
        return this;
    }

    /**
     * Get the referenceName property: Reference SQL pool name.
     *
     * @return the referenceName value.
     */
    public String getReferenceName() {
        return this.referenceName;
    }

    /**
     * Set the referenceName property: Reference SQL pool name.
     *
     * @param referenceName the referenceName value to set.
     * @return the SqlPoolReference object itself.
     */
    public SqlPoolReference setReferenceName(String referenceName) {
        this.referenceName = referenceName;
        return this;
    }
}
