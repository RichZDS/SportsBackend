package com.zds.sports.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateCourseDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Coach ID is required")
    private Long coachId;

    @NotNull(message = "Capacity is required")
    private Integer capacity;

    @NotNull(message = "Start time is required")
    private LocalDateTime startAt;

    @NotNull(message = "End time is required")
    private LocalDateTime endAt;

    @NotBlank(message = "Status is required")
    private String status;
}
