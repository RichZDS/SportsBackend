package com.zds.sports.model.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EmployeeVO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String position;
    private String status;
    private BigDecimal salary;
    private LocalDateTime hiredAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


