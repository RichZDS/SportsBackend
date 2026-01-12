package com.zds.sports.model.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TrainerVO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String specialization;
    private Integer experienceYears;
    private String status;
    private BigDecimal hourlyRate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


