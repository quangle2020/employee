package com.quanglv.gateway.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class TokenRequestDTO implements Serializable {
    //grant_type=password&username=admin&password=123456&scope=trust
    private String grant_type = "password";
    private String username;
    private String password;
    private String scope = "trust";
}
