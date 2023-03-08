package com.certification.employeeservice.service.impl;

import com.certification.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentClient {

    @GetMapping("api/departments/get/{department-code}")
    DepartmentDto getDepartmentByCode (@PathVariable("department-code") String departmentCode);
}
