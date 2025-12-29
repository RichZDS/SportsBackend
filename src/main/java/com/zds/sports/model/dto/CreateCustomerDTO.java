package com.zds.sports.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCustomerDTO {
    @NotBlank(message = "Name is required")
    private String name;
    private String phone;
    private String email;
    @NotBlank(message = "Status is required")
    private String status;
}
