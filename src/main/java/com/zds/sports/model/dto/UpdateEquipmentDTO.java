package com.zds.sports.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateEquipmentDTO {
    private String name;
    private String category;
    private String location;
    private LocalDate purchaseDate;
    private String status;
}
