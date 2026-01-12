package com.zds.sports.model.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CourseVO {
    private Long id;
    private String name;
    private Long trainerId;
    private String trainerName;
    private String description;
    private Integer durationMinutes;
    private Integer maxParticipants;
    private BigDecimal price;
    private LocalDateTime scheduleTime;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


