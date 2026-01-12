package com.zds.sports.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreateEquipmentDTO {
    @NotNull
    private String name;
    @NotNull
    private String category;
    private String brand;
    private String model;
    private String purchaseDate; // YYYY-MM-DD format
    private BigDecimal purchasePrice;
    private String status;
    private String location;
    private String maintenanceDate; // YYYY-MM-DD format
}

