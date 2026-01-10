package com.zds.sports.model.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EmployeeVO {
    private Long id;
    private String name;
    private String phone;
    private String role;
    private LocalDate hireDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
