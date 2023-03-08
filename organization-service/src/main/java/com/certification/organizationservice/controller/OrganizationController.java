package com.certification.organizationservice.controller;

import com.certification.organizationservice.dto.OrganizationDto;
import com.certification.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private OrganizationService organizationService;

    @PostMapping("/save")
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto) {
        OrganizationDto savedOrganizationDto = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganizationDto, HttpStatus.CREATED);
    }

    @GetMapping("/get/{organizationCode}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode (@PathVariable String organizationCode) {
        OrganizationDto fetchedOrganizationDto = organizationService.getOrganizationByCode(organizationCode);
        return new ResponseEntity<>(fetchedOrganizationDto, HttpStatus.OK);
    }
}
