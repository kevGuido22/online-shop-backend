package com.keving.online_shop.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponseDTO {
    private Long id;
    private String email;
    private String username;
}
