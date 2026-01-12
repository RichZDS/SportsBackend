package com.zds.sports.model.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UpdateRecordDTO {
    private BigDecimal amount;
    private String category;
    private String remark;
    private LocalDateTime paidAt;
}
