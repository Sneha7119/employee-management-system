package com.certification.employeeservice.service.impl;

import com.certification.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
//@FeignClient(url = "http://localhost:8083", value = "ORGANIZATION-SERVICE")
public interface OrganizationClient {

    @GetMapping("/api/organizations/get/{organizationCode}")
    OrganizationDto getOrganizationByCode (@PathVariable String organizationCode);
}
