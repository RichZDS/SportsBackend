package com.zds.sports.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateMemberDTO {
    private String name;
    private String phone;
    private String email;
    private String level;
    private String status;
    private LocalDate joinDate;
}
