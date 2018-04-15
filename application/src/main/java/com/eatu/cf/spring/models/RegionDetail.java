package com.eatu.cf.spring.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import com.sap.cloud.sdk.result.ElementName;

@Data
public class RegionDetail{

    @ElementName( "RegionID" )
    @JsonProperty("RegionID")
    public int RegionID;

    @ElementName( "RegionDescription" )
    @JsonProperty("RegionDescription")
    public String RegionDescription;

}


