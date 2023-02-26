package com.certification.employeeservice.service.impl;

import com.certification.employeeservice.dto.EmployeeDto;
import com.certification.employeeservice.entity.Employee;
import com.certification.employeeservice.repository.EmployeeRepository;
import com.certification.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee savedEmployee = employeeRepository.save(modelMapper.map(employeeDto, Employee.class));
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee fetchedEmployee = employeeRepository.findById(id).get();
        return modelMapper.map(fetchedEmployee, EmployeeDto.class);
    }
}
