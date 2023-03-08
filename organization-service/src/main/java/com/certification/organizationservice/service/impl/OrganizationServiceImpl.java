package com.certification.organizationservice.service.impl;

import com.certification.organizationservice.dto.OrganizationDto;
import com.certification.organizationservice.entity.Organization;
import com.certification.organizationservice.repository.OrganizationRepository;
import com.certification.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    private ModelMapper modelMapper;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization savedOrganization = organizationRepository
                .save(modelMapper.map(organizationDto, Organization.class));
        return modelMapper.map(savedOrganization, OrganizationDto.class);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization fetchedOrganization = organizationRepository.findByOrganizationCode(organizationCode).get();
        return modelMapper.map(fetchedOrganization, OrganizationDto.class);
    }
}
