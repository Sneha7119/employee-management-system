package com.certification.employeeservice.service.impl;

import com.certification.employeeservice.dto.ApiResponseDto;
import com.certification.employeeservice.dto.DepartmentDto;
import com.certification.employeeservice.dto.EmployeeDto;
import com.certification.employeeservice.entity.Employee;
import com.certification.employeeservice.repository.EmployeeRepository;
import com.certification.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private RestTemplate restTemplate;

    private ModelMapper modelMapper;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee savedEmployee = employeeRepository.save(modelMapper.map(employeeDto, Employee.class));
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public ApiResponseDto getEmployeeById(Long id) {
        Employee fetchedEmployee = employeeRepository.findById(id).get();
        ResponseEntity<DepartmentDto> departmentDtoResponseEntity = restTemplate.
                getForEntity(
                        "http://localhost:8080/api/departments/get/" + fetchedEmployee.getDepartmentCode(),
                        DepartmentDto.class);
        DepartmentDto departmentDto = departmentDtoResponseEntity.getBody();
        ApiResponseDto apiResponseDto = new ApiResponseDto(
                modelMapper.map(fetchedEmployee, EmployeeDto.class),
                departmentDto);
        return apiResponseDto;
    }
}
