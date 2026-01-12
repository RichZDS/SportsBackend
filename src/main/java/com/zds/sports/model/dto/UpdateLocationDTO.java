package com.zds.sports.model.dto;

import lombok.Data;

@Data
public class UpdateLocationDTO {
    private String name;
    private String address;
    private String phone;
    private String manager;
    private String status;
    private String description;
}

