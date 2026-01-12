package com.zds.sports.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("equipment_usages")
public class EquipmentUsageDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long customerId;
    private Long equipmentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status; // USING/FINISHED/CANCELLED
    private String remark;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @TableField(exist = false)
    private CustomerDO customer;

    @TableField(exist = false)
    private EquipmentDO equipment;
}

