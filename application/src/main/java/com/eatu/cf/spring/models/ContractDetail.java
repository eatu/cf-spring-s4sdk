package com.eatu.cf.spring.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import com.sap.cloud.sdk.result.ElementName;

@Data
public class ContractDetail {


    @ElementName( "ContractID" )
    @JsonProperty("ContractID")
    public String ContractID;

    @ElementName( "ContractAccountID" )
    @JsonProperty("ContractAccountID")
    public String ContractAccountID;

    @ElementName( "DivisionID" )
    public String DivisionID;

    @ElementName( "PremiseID" )
    public String PremiseID;

    @ElementName( "Description" )
    public String Description;
}
