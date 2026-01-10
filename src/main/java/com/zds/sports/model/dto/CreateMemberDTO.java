package com.zds.sports.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateMemberDTO {
    @NotBlank(message = "Name is required")
    private String name;

    private String phone;

    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Level is required")
    private String level;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Join date is required")
    private LocalDate joinDate;
}
