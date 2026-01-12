package com.zds.sports.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class UpdateTrainerDTO {
    private String name;
    private String phone;
    private String email;
    private String specialization;
    private Integer experienceYears;
    private String status;
    private BigDecimal hourlyRate;
}


