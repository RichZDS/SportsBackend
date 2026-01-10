package com.zds.sports.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ConsumptionVO {
    private Long id;
    private Long memberId;
    private String memberName;
    private String memberPhone;
    private BigDecimal amount;
    private String category;
    private String remark;
    private LocalDateTime paidAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
