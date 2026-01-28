package com.keving.online_shop.auth.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}
