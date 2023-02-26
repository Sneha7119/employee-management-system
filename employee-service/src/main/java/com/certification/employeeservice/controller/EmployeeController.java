package com.certification.employeeservice.controller;

import com.certification.employeeservice.dto.EmployeeDto;
import com.certification.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> saveEmployee (@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById (@PathVariable Long id) {
        EmployeeDto fetchedEmployee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(fetchedEmployee, HttpStatus.OK);
    }
}
