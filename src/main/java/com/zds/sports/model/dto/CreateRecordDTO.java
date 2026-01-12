package com.zds.sports.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateRecordDTO {
    @NotNull
    private Long customerId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private String category;
    private String remark;
    @NotNull
    private LocalDateTime paidAt;
}
