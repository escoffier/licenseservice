package com.licenseservice.Client;

import com.licenseservice.Model.Organization;
import com.licenseservice.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class OrganizationRestTemplateClient {

    private final static Logger logger = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate = null;

    public Organization getOrganization(Long organizationId) {
        //logger.debug("In Licensing Service.getOrganization: {}", UserContext.getCorrelationId());

        ResponseEntity<Organization> responseEntity = restTemplate.exchange(
                "http://organizationservice/v1/organizations/{organizationId}",
                //"http://zuulservice/api/organization/v1/organizations/{organizationId}",
                HttpMethod.GET, null, Organization.class, organizationId);

        return responseEntity.getBody();
    }
}
