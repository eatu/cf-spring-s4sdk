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


import com.eatu.cf.spring.models.ContractDetail;

@RestController
@RequestMapping( "/contracts" )
public class ContractController {

    private static final Logger logger = CloudLoggerFactory.getLogger(ContractController.class);

    @RequestMapping( method = RequestMethod.GET )
    public ResponseEntity<ContractDetail> getContracts( @RequestParam( defaultValue = "001" ) final String ContractID,
        @RequestParam( defaultValue = "001a" ) final String ContractAccountID )
    {
        logger.info("I am running!");
        ContractDetail contract = new ContractDetail();
        List<ContractDetail> contracts;
        try {
            contracts = ODataQueryBuilder
                    .withEntity("/sap/opu/odata/sap/ERP_UTILITIES_UMC", "Contracts")
                    .select("ContractID", "ContractAccountID", "DivisionID", "PremiseID", "Description")
                    .build()
                    .execute(new ErpConfigContext("ErpQueryEndpoint"))
                    .asList(ContractDetail.class);
            return ResponseEntity.ok(contract);

        } catch (ODataException e) {
            logger.error(e.getMessage(), e);
            System.out.println("ContractsServlet->ERROR" + "Query error:" + e.getMessage() );
            return ResponseEntity.badRequest().body(contract);
        }

    }
}
