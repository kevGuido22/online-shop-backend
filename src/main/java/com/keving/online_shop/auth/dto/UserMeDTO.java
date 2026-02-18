package com.keving.online_shop.auth.dto;

import java.util.List;

public record UserMeDTO(
        Long id,
        String email,
        List<String> roles
) {
}
