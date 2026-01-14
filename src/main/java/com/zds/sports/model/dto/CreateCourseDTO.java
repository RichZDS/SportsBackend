package com.zds.sports.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.lang.String;

@Data
public class CreateCourseDTO {
    @NotNull
    private String name;
    private Long trainerId;
    private String description;
    @NotNull
    private Integer durationMinutes;
    @NotNull
    private Integer maxParticipants;
    @NotNull
    private BigDecimal price;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String scheduleTime;
    @NotNull
    private String status;
}


