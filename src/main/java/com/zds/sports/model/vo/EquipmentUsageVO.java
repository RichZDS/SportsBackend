package com.zds.sports.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EquipmentUsageVO {
    private Long id;
    private Long customerId;
    private String customerName;
    private Long equipmentId;
    private String equipmentName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String remark;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

