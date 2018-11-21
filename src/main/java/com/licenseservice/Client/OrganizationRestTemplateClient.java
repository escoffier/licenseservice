package com.licenseservice.Client;

import com.licenseservice.Model.License;
import com.licenseservice.Model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganizationRestTemplateClient {

    @Autowired
    private RestTemplate restTemplate = null;

    public Organization getOrganization(Long organizationId) {
        ResponseEntity<Organization> responseEntity = restTemplate.exchange(
                "http://organizationservice//v1/organizations/{organizationId}",
                HttpMethod.GET, null, Organization.class, organizationId);

        return responseEntity.getBody();
    }
}
