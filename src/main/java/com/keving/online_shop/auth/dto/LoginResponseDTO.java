package com.keving.online_shop.auth.dto;

import com.keving.online_shop.auth.model.RefreshToken;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class LoginResponseDTO {
    private String token;
    private RefreshToken refreshToken;
    private long expiresIn;
}
