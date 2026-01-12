package com.zds.sports.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateEmployeeDTO {
    @NotNull
    private String name;
    @NotNull
    private String phone;
    private String email;
    @NotNull
    private String position;
    private String status;
    @NotNull
    private BigDecimal salary;
    @NotNull
    private LocalDateTime hiredAt;
}


