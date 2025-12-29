package com.zds.sports.model.dto;

import lombok.Data;

@Data
public class UpdateCustomerDTO {
    private String name;
    private String phone;
    private String email;
    private String status;
}
