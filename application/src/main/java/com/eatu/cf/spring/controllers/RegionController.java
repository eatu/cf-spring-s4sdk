package com.eatu.cf.spring.controllers;


import org.slf4j.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import com.google.gson.Gson;

import com.sap.cloud.sdk.s4hana.connectivity.ErpConfigContext;
import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryBuilder;


import com.eatu.cf.spring.models.RegionDetail;

@RestController
@RequestMapping( "/regions" )

public class RegionController {

    private static final Logger logger = CloudLoggerFactory.getLogger(RegionController.class);

    @RequestMapping( method = RequestMethod.GET )
    public ResponseEntity<List<RegionDetail>> getRegions(
            @RequestParam( defaultValue = "1" ) final int RegionID,
            @RequestParam( defaultValue = "Desc" ) final String RegionDescription )
    {
        logger.info("I am running!");
        RegionDetail contract = new RegionDetail();
        List<RegionDetail> regions;
        try {
            regions = ODataQueryBuilder
                    .withEntity("/V2/Northwind/Northwind.svc", "Regions")
                    .select("RegionID", "RegionDescription")
                    .build()
                    .execute(new ErpConfigContext("Northwind"))
                    .asList(RegionDetail.class);
            return ResponseEntity.ok(regions);

        } catch (ODataException e) {
            logger.error(e.getMessage(), e);
            System.out.println("RegionController->ERROR" + "Query error:" + e.getMessage() );
            return ResponseEntity.badRequest().build();
        }

    }
}
