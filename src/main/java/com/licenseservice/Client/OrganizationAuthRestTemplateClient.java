package com.licenseservice.Client;

import com.licenseservice.Model.Organization;
import com.licenseservice.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrganizationAuthRestTemplateClient {


        private final static Logger logger = LoggerFactory.getLogger(com.licenseservice.Client.OrganizationRestTemplateClient.class);

        @Autowired
        @Qualifier("oAuth2RestTemplate")
        private OAuth2RestOperations auth2RestTemplate = null;

        public Organization getOrganization(Long organizationId) {
            //logger.debug("In Licensing Service.getOrganization: {}", UserContext.getCorrelationId());

            ResponseEntity<Organization> responseEntity = auth2RestTemplate.exchange(
                    //"http://organizationservice/v1/organizations/{organizationId}",
                    "http://zuulservice/api/organization/v1/organizations/{organizationId}",
                    HttpMethod.GET, null, Organization.class, organizationId);

            return responseEntity.getBody();
        }
}
