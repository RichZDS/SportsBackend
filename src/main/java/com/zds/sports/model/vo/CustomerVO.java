package com.zds.sports.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerVO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
