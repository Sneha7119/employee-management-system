package com.certification.employeeservice.service.impl;

import com.certification.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("api/departments/get/{department-code}")
    DepartmentDto getDepartmentByCode (@PathVariable("department-code") String departmentCode);
}
