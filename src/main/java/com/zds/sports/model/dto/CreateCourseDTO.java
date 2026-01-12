package com.zds.sports.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateCourseDTO {
    @NotNull
    private String name;
    private Long trainerId;
    private String description;
    @NotNull
    private Integer durationMinutes;
    @NotNull
    private Integer maxParticipants;
    @NotNull
    private BigDecimal price;
    @NotNull
    private LocalDateTime scheduleTime;
    @NotNull
    private String status;
}


