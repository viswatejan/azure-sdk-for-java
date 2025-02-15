// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.security.generated;

import com.azure.core.util.BinaryData;
import com.azure.resourcemanager.security.models.SecurityContactList;

public final class SecurityContactListTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        SecurityContactList model =
            BinaryData
                .fromString(
                    "{\"value\":[{\"properties\":{\"emails\":\"ux\",\"phone\":\"q\"},\"id\":\"syuuximerq\",\"name\":\"obwyznkb\",\"type\":\"kutwpf\"},{\"properties\":{\"emails\":\"gmhrskdsnfdsdoak\",\"phone\":\"dlmkkzevdl\"},\"id\":\"pusdstt\",\"name\":\"vogvbbejdcngqqmo\",\"type\":\"kufgmj\"},{\"properties\":{\"emails\":\"rdgrtw\",\"phone\":\"nuuzkopbm\"},\"id\":\"fdwoyuhh\",\"name\":\"iuiefozbhdmsm\",\"type\":\"mzqhoftrmaequi\"},{\"properties\":{\"emails\":\"icslfaoq\",\"phone\":\"iyylhalnswhccsp\"},\"id\":\"ivwitqscywugg\",\"name\":\"oluhczbwemh\",\"type\":\"i\"}],\"nextLink\":\"brgz\"}")
                .toObject(SecurityContactList.class);
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        SecurityContactList model = new SecurityContactList();
        model = BinaryData.fromObject(model).toObject(SecurityContactList.class);
    }
}
