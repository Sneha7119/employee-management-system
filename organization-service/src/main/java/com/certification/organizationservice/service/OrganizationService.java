package com.certification.organizationservice.service;

import com.certification.organizationservice.dto.OrganizationDto;

public interface OrganizationService {

    OrganizationDto saveOrganization (OrganizationDto organizationDto);

    OrganizationDto getOrganizationByCode (String organizationCode);
}
