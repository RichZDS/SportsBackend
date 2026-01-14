package com.zds.sports.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.lang.String;

@Data
public class CreateEquipmentUsageDTO {
    @NotNull
    private Long customerId;
    @NotNull
    private Long equipmentId;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String startTime;
}

