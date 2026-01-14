package com.zds.sports.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.lang.String;

@Data
public class UpdateEquipmentUsageDTO {
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String endTime;
    private String remark;
}

