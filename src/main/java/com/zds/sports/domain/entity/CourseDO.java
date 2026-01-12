package com.zds.sports.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("courses")
public class CourseDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Long trainerId;

    private String description;

    private Integer durationMinutes;

    private Integer maxParticipants;

    private BigDecimal price;

    private LocalDateTime scheduleTime;

    private String status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @TableField(exist = false)
    private TrainerDO trainer;
}


