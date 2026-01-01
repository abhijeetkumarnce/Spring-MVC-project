package com.week2.homework.service.impl;

import com.week2.homework.dto.DepartmentDTO;
import com.week2.homework.entity.Department;
import com.week2.homework.exception.ResourceNotFoundException;
import com.week2.homework.repository.DepartmentRepository;
import com.week2.homework.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<DepartmentDTO> getAllDepartment() {
        List<Department> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DepartmentDTO> getDepartmentById(Long id) {
        return departmentRepository.findById(id).
                map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }

    @Override
    public DepartmentDTO createNewDepartment(DepartmentDTO departmentDTO) {
        Department toSaveDepartment = modelMapper.map(departmentDTO, Department.class);
        Department savedDepartment = departmentRepository.save(toSaveDepartment);

        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO updateDepartmentById(Long id, DepartmentDTO departmentDTO) {
        isExistsDepartment(id);
        Department departmentEntity = modelMapper.map(departmentDTO, Department.class);
        departmentEntity.setId(id);
        Department savedDepartment = departmentRepository.save(departmentEntity);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    @Override
    public boolean deleteDepartmentById(Long id) {
        isExistsDepartment(id);
        departmentRepository.deleteById(id);
        return true;
    }

    //INTERNAL FUNCTION
    public void isExistsDepartment(Long id) {
        boolean exists = departmentRepository.existsById(id);
        if (!exists)
            throw new ResourceNotFoundException("Department with id "+id+" is not found.");
    }
}
