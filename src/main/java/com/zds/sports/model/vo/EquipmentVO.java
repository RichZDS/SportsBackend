package com.zds.sports.model.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EquipmentVO {
    private Long id;
    private String name;
    private String category;
    private String location;
    private LocalDate purchaseDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
