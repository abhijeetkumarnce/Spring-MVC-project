package com.week2.homework.controller;

import com.week2.homework.dto.DepartmentDTO;
import com.week2.homework.exception.ResourceNotFoundException;
import com.week2.homework.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment() {
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(id);
        return departmentDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Department with id "+id+" is not found."));
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
        return ResponseEntity.ok(departmentService.createNewDepartment(departmentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentDTO departmentDTO) {
        return ResponseEntity.ok(departmentService.updateDepartmentById(id, departmentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long id) {
        boolean gotDeleted = departmentService.deleteDepartmentById(id);
        if (gotDeleted)
            return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }
}
