package com.zds.sports.controller;

import com.zds.sports.logic.EmployeeLogic;
import com.zds.sports.model.dto.CreateEmployeeDTO;
import com.zds.sports.model.dto.UpdateEmployeeDTO;
import com.zds.sports.model.vo.EmployeeVO;
import com.zds.sports.model.vo.PageResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employee Management", description = "APIs for managing employees")
public class EmployeeController {

    private final EmployeeLogic employeeLogic;

    @GetMapping
    @Operation(summary = "Get employees list with pagination and search")
    public PageResultVO<EmployeeVO> getEmployees(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword) {
        return employeeLogic.getEmployees(page, size, keyword);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new employee")
    public EmployeeVO createEmployee(@Valid @RequestBody CreateEmployeeDTO createEmployeeDTO) {
        return employeeLogic.createEmployee(createEmployeeDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing employee")
    public EmployeeVO updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEmployeeDTO updateEmployeeDTO) {
        return employeeLogic.updateEmployee(id, updateEmployeeDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an employee")
    public void deleteEmployee(@PathVariable Long id) {
        employeeLogic.deleteEmployee(id);
    }
}
