package com.zds.sports.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateStaffDTO {
    @NotNull
    private String name;

    private String phone;

    private String email;

    @NotNull
    private Long locationId;

    @NotNull
    private String position;

    private String status;

    private BigDecimal salary;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime hiredAt;
}

