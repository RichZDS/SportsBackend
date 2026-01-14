package com.zds.sports.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.lang.String;

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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String hiredAt;
}


