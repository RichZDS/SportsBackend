package com.zds.sports.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.lang.String;

@Data
public class CreateRecordDTO {
    @NotNull
    private Long customerId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private String category;
    private String remark;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String paidAt;
}
