package com.zds.sports.model.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RecordVO {
    private Long id;
    private Long customerId;
    private String customerName;
    private BigDecimal amount;
    private String category;
    private String remark;
    private LocalDateTime paidAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
