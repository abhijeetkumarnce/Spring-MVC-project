package com.week2.homework.service;

import com.week2.homework.dto.DepartmentDTO;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<DepartmentDTO> getAllDepartment();

    Optional<DepartmentDTO> getDepartmentById(Long id);

    DepartmentDTO createNewDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO updateDepartmentById(Long id, DepartmentDTO departmentDTO);

    boolean deleteDepartmentById(Long id);
}
