package com.certification.departmentservice.controller;

import com.certification.departmentservice.dto.DepartmentDto;
import com.certification.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    // Save/Create Department REST API
    @PostMapping("/create")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    @GetMapping("/get/{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode (@PathVariable("department-code") String departmentCode) {
        DepartmentDto fetchedDepartmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(fetchedDepartmentDto, HttpStatus.OK);
    }
}
