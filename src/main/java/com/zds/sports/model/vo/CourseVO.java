package com.zds.sports.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseVO {
    private Long id;
    private String name;
    private Long coachId;
    private String coachName;
    private String coachPhone;
    private Integer capacity;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
