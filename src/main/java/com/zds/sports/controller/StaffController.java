package com.zds.sports.controller;

import com.zds.sports.model.dto.CreateStaffDTO;
import com.zds.sports.model.dto.UpdateStaffDTO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.model.vo.StaffVO;
import com.zds.sports.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
@Tag(name = "Staff Management", description = "APIs for managing staff")
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    @Operation(summary = "Get staff list with pagination and search")
    public PageResultVO<StaffVO> getStaff(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long locationId) {
        return staffService.getStaff(page, size, keyword, locationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new staff")
    public StaffVO createStaff(@Valid @RequestBody CreateStaffDTO createStaffDTO) {
        return staffService.createStaff(createStaffDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing staff")
    public StaffVO updateStaff(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStaffDTO updateStaffDTO) {
        return staffService.updateStaff(id, updateStaffDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a staff")
    public void deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
    }
}

