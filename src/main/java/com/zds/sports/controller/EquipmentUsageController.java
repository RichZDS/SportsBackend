package com.zds.sports.controller;

import com.zds.sports.model.dto.CreateEquipmentUsageDTO;
import com.zds.sports.model.dto.UpdateEquipmentUsageDTO;
import com.zds.sports.model.vo.EquipmentUsageVO;
import com.zds.sports.service.EquipmentUsageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipment-usages")
@RequiredArgsConstructor
@Tag(name = "Equipment Usage", description = "APIs for tracking equipment usage")
public class EquipmentUsageController {

    private final EquipmentUsageService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an equipment usage record")
    public EquipmentUsageVO create(@Valid @RequestBody CreateEquipmentUsageDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an equipment usage record")
    public EquipmentUsageVO update(@PathVariable Long id, @Valid @RequestBody UpdateEquipmentUsageDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an equipment usage record")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}

