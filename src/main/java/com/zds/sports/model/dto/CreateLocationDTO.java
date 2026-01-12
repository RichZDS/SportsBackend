package com.zds.sports.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateLocationDTO {
    @NotNull
    private String name;

    private String address;

    private String phone;

    private String manager;

    private String status;

    private String description;
}

