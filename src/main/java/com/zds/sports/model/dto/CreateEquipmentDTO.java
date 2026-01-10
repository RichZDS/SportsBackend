package com.zds.sports.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEquipmentDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    private String location;

    @NotNull(message = "Purchase date is required")
    private LocalDate purchaseDate;

    @NotBlank(message = "Status is required")
    private String status;
}
