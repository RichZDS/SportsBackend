package com.zds.sports.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.lang.String;

@Data
public class UpdateEmployeeDTO {
    private String name;
    private String phone;
    private String email;
    private String position;
    private String status;
    private BigDecimal salary;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String hiredAt;
}


