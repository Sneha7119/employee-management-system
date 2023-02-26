package com.certification.employeeservice.service;

import com.certification.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee (EmployeeDto employeeDto);

    EmployeeDto getEmployeeById (Long id);
}
