package com.zds.sports.model.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UpdateCourseDTO {
    private String name;
    private Long trainerId;
    private String description;
    private Integer durationMinutes;
    private Integer maxParticipants;
    private BigDecimal price;
    private LocalDateTime scheduleTime;
    private String status;
}


