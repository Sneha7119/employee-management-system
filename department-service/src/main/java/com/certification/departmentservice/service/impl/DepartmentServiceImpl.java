package com.certification.departmentservice.service.impl;

import com.certification.departmentservice.dto.DepartmentDto;
import com.certification.departmentservice.entity.Department;
import com.certification.departmentservice.repository.DepartmentRepository;
import com.certification.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department savedDepartment = departmentRepository.save(modelMapper.map(departmentDto, Department.class));
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department fetchedDepartment = departmentRepository.findByDepartmentCode(departmentCode).get();
        return modelMapper.map(fetchedDepartment, DepartmentDto.class);
    }

}
