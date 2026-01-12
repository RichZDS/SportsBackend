package com.zds.sports.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCourseBookingDTO {
    @NotNull
    private Long customerId;
    @NotNull
    private Long courseId;
}

