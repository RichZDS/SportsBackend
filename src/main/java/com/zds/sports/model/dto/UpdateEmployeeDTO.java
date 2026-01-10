package com.zds.sports.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateEmployeeDTO {
    private String name;
    private String phone;
    private String role;
    private LocalDate hireDate;
    private String status;
}
