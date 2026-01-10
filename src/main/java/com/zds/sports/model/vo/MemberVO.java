package com.zds.sports.model.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MemberVO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String level;
    private String status;
    private LocalDate joinDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
