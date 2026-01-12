package com.zds.sports.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateEquipmentUsageDTO {
    @NotNull
    private Long customerId;
    @NotNull
    private Long equipmentId;
    @NotNull
    private LocalDateTime startTime;
}

