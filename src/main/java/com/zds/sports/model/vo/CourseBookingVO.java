package com.zds.sports.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseBookingVO {
    private Long id;
    private Long customerId;
    private String customerName;
    private Long courseId;
    private String courseName;
    private String status;
    private LocalDateTime bookedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

