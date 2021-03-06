package com.licenseservice.Service;

import com.licenseservice.Client.OrganizationAuthRestTemplateClient;
import com.licenseservice.Client.OrganizationDiscoveryClient;
import com.licenseservice.Client.OrganizationFeignClient;
import com.licenseservice.Client.OrganizationRestTemplateClient;
import com.licenseservice.Config.ServiceConfig;
import com.licenseservice.Mapper.LicenseMapper;
import com.licenseservice.Model.License;
import com.licenseservice.Model.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {

    private Logger logger = LoggerFactory.getLogger(LicenseService.class);

    @Autowired
    private LicenseMapper licenseMapper = null;

    @Autowired
    private OrganizationDiscoveryClient discoveryClient  = null;

    @Autowired
    private OrganizationRestTemplateClient restTemplateClient = null;

    @Autowired
    private OrganizationAuthRestTemplateClient authRestTemplateClient = null;

    @Autowired
    private OrganizationFeignClient feignClient = null;

    @Autowired
    private ServiceConfig serviceConfig = null;

    private Organization retrieveOrgInfo(Long organizationId, String clientType){
        Organization organization = null;

        switch (clientType){
            case "discovery":
                logger.info("---discovery client");
                organization = discoveryClient.getOrganization(organizationId);
                break;
            case "rest":
                logger.info("---rest client");
                organization = restTemplateClient.getOrganization(organizationId);
                break;
            case "feign":
                logger.info("---feign client");
                organization = feignClient.getOrganization(organizationId);
                break;
            case "authrest":
                logger.info("---auth rest client");
                organization = authRestTemplateClient.getOrganization(organizationId);
        }

        return organization;

    }

    public License getLicense(Long organizationId, Long licenseId, String clientType) {
        License license = licenseMapper.getLicense(organizationId, licenseId);

        Organization org = retrieveOrgInfo(organizationId, clientType);

        license.setOrganizationName(org.getName());
        license.setContactName(org.getContactName());
        license.setContactEmail(org.getContactEmail());
        license.setContactPhone(org.getContactPhone());
        license.setComment(serviceConfig.getExampleProperty());

        return license;
    }
}
