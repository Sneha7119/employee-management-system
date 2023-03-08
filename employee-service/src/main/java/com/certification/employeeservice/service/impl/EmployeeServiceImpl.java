package com.certification.employeeservice.service.impl;

import com.certification.employeeservice.dto.ApiResponseDto;
import com.certification.employeeservice.dto.DepartmentDto;
import com.certification.employeeservice.dto.EmployeeDto;
import com.certification.employeeservice.entity.Employee;
import com.certification.employeeservice.repository.EmployeeRepository;
import com.certification.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    // private RestTemplate restTemplate;
    //private WebClient webClient;
    private APIClient apiClient;

    private ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee savedEmployee = employeeRepository.save(modelMapper.map(employeeDto, Employee.class));
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public ApiResponseDto getEmployeeById(Long id) {
        LOGGER.info("[EmployeeServiceImpl] -> Starting getEmployeeById()");
        Employee fetchedEmployee = employeeRepository.findById(id).get();

        /*
        ********************* Using RestTemplate to communicate with Department service.*******************************
        ResponseEntity<DepartmentDto> departmentDtoResponseEntity = restTemplate.
                getForEntity(
                        "http://localhost:8080/api/departments/get/" + fetchedEmployee.getDepartmentCode(),
                        DepartmentDto.class);
        DepartmentDto departmentDto = departmentDtoResponseEntity.getBody();*/

        /*
        *********************** Using WebClient to communicate with Department service ******************************
        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/get/" + fetchedEmployee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();         // block()  -> specifies synchronous call*/

        DepartmentDto departmentDto = apiClient.getDepartmentByCode(fetchedEmployee.getDepartmentCode());
        return new ApiResponseDto(
                modelMapper.map(fetchedEmployee, EmployeeDto.class),
                departmentDto);
    }

    public ApiResponseDto getDefaultDepartment(Long id, Exception exception) {
        LOGGER.info("[EmployeeServiceImpl] -> Starting getDefaultDepartment()");
        Employee fetchedEmployee = employeeRepository.findById(id).get();
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("Default Dep");
        departmentDto.setDepartmentCode("DD");
        departmentDto.setDepartmentDescription("Default Department since Deaprtment service is down");

        return new ApiResponseDto(
                modelMapper.map(fetchedEmployee, EmployeeDto.class),
                departmentDto);
    }
}
