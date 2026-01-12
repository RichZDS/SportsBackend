package com.zds.sports.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateEquipmentUsageDTO {
    private String status;
    private LocalDateTime endTime;
    private String remark;
}

