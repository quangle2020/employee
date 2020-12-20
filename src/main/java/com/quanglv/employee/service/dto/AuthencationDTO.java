package com.quanglv.employee.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class AuthencationDTO {
    String grant_type = "password";
    String username = "admin";
    String password = "123456";
    String scope = "trust";
}
