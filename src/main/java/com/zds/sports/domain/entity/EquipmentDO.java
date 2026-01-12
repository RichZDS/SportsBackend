package com.zds.sports.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("equipment")
public class EquipmentDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String category;

    private String brand;

    private String model;

    private LocalDateTime purchaseDate;

    private BigDecimal purchasePrice;

    private String status;

    private String location;

    private LocalDateTime maintenanceDate;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}


