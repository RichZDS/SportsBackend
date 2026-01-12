package com.zds.sports.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreateTrainerDTO {
    @NotNull
    private String name;
    @NotNull
    private String phone;
    private String email;
    private String specialization;
    private Integer experienceYears;
    private String status;
    private BigDecimal hourlyRate;
}


