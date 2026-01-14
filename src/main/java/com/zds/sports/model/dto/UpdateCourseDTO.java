package com.zds.sports.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.lang.String;

@Data
public class UpdateCourseDTO {
    private String name;
    private Long trainerId;
    private String description;
    private Integer durationMinutes;
    private Integer maxParticipants;
    private BigDecimal price;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String scheduleTime;
    private String status;
}


