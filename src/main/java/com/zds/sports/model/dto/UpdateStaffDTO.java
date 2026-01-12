package com.zds.sports.model.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UpdateStaffDTO {
    private String name;
    private String phone;
    private String email;
    private Long locationId;
    private String position;
    private String status;
    private BigDecimal salary;
    private LocalDateTime hiredAt;
}

