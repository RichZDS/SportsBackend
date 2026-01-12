package com.zds.sports.model.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EquipmentVO {
    private Long id;
    private String name;
    private String category;
    private String brand;
    private String model;
    private LocalDateTime purchaseDate;
    private BigDecimal purchasePrice;
    private String status;
    private String location;
    private LocalDateTime maintenanceDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


