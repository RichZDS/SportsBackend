package com.zds.sports.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateCourseDTO {
    private String name;
    private Long coachId;
    private Integer capacity;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String status;
}
