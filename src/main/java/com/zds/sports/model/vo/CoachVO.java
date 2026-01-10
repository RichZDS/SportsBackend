package com.zds.sports.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CoachVO {
    private Long id;
    private String name;
    private String phone;
    private String specialty;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
