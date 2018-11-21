package com.licenseservice.Controller;

import com.licenseservice.Model.License;
import com.licenseservice.Service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="v1/organizations/{organizationId}/licenses")
public class LicenseController {

    @Autowired
    private LicenseService licenseService = null;

    @GetMapping("/{licenseId}/{clientType}")
    public License getLicense(@PathVariable("organizationId") Long organizationId,
                              @PathVariable("licenseId") Long licenseId,
                              @PathVariable("clientType") String clientType) {
        return licenseService.getLicense(organizationId, licenseId, clientType);
    }

}
