package com.zds.sports.controller;

import com.zds.sports.logic.ConsumptionLogic;
import com.zds.sports.model.dto.CreateConsumptionDTO;
import com.zds.sports.model.dto.UpdateConsumptionDTO;
import com.zds.sports.model.vo.ConsumptionVO;
import com.zds.sports.model.vo.PageResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/consumptions")
@RequiredArgsConstructor
@Tag(name = "Consumption Management", description = "APIs for managing member consumptions")
public class ConsumptionController {

    private final ConsumptionLogic consumptionLogic;

    @GetMapping
    @Operation(summary = "Get consumptions list with pagination and filters")
    public PageResultVO<ConsumptionVO> getConsumptions(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end) {
        return consumptionLogic.getConsumptions(page, size, memberId, category, start, end);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new consumption")
    public ConsumptionVO createConsumption(@Valid @RequestBody CreateConsumptionDTO createConsumptionDTO) {
        return consumptionLogic.createConsumption(createConsumptionDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing consumption")
    public ConsumptionVO updateConsumption(
            @PathVariable Long id,
            @Valid @RequestBody UpdateConsumptionDTO updateConsumptionDTO) {
        return consumptionLogic.updateConsumption(id, updateConsumptionDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a consumption")
    public void deleteConsumption(@PathVariable Long id) {
        consumptionLogic.deleteConsumption(id);
    }
}
