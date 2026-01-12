package com.zds.sports.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("consume_records")
public class ConsumeRecordDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long customerId;

    private BigDecimal amount;

    private String category;

    private String remark;

    private LocalDateTime paidAt;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
    
    // For MapStruct convenience (optional, but generated code didn't have relations)
    @TableField(exist = false)
    private CustomerDO customer;
}
