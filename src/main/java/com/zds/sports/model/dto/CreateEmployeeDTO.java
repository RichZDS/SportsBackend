package com.zds.sports.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEmployeeDTO {
    @NotBlank(message = "Name is required")
    private String name;

    private String phone;

    @NotBlank(message = "Role is required")
    private String role;

    @NotNull(message = "Hire date is required")
    private LocalDate hireDate;

    @NotBlank(message = "Status is required")
    private String status;
}
