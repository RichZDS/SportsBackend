package com.zds.sports.model.dto;

import lombok.Data;

@Data
public class UpdateCoachDTO {
    private String name;
    private String phone;
    private String specialty;
    private String status;
}
