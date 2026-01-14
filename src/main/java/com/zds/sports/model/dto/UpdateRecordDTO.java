package com.zds.sports.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.lang.String;

@Data
public class UpdateRecordDTO {
    private BigDecimal amount;
    private String category;
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String paidAt;
}
