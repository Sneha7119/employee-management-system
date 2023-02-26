package com.certification.employeeservice.service;

import com.certification.employeeservice.dto.ApiResponseDto;
import com.certification.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee (EmployeeDto employeeDto);

    ApiResponseDto getEmployeeById (Long id);
}
