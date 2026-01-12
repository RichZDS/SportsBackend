package com.zds.sports.controller;

import com.zds.sports.model.dto.CreateEquipmentDTO;
import com.zds.sports.model.dto.UpdateEquipmentDTO;
import com.zds.sports.model.vo.EquipmentVO;
import com.zds.sports.model.vo.PageResultVO;
import com.zds.sports.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
@Tag(name = "Equipment Management", description = "APIs for managing equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping
    @Operation(summary = "Get equipment list with pagination and search")
    public PageResultVO<EquipmentVO> getEquipment(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String status) {
        return equipmentService.getEquipment(page, size, keyword, category, status);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new equipment")
    public EquipmentVO createEquipment(@Valid @RequestBody CreateEquipmentDTO createEquipmentDTO) {
        return equipmentService.createEquipment(createEquipmentDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing equipment")
    public EquipmentVO updateEquipment(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEquipmentDTO updateEquipmentDTO) {
        return equipmentService.updateEquipment(id, updateEquipmentDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete equipment")
    public void deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
    }
}

