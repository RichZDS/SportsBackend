package com.zds.sports.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCustomerDTO {
    @NotNull
    private String name;
    @NotNull
    private String phone;
    private String email;
    private String status;
}
