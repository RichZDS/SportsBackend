package com.zds.sports.model.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LocationVO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String manager;
    private String status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

