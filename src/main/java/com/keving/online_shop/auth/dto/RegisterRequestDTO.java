package com.keving.online_shop.auth.dto;

import com.keving.online_shop.utils.RegexConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @NotBlank(message = "The username is required")
    @Size(min = 5, max = 50, message = "The username must be between 5 and 50 characters")
    private String username;

    @NotBlank(message = "The email is required")
    @Email(message = "The email format is not valid")
    @Size(max = 100, message = "The email must be less than 100 characters")
    private String email;

    @NotBlank(message = "The password is required")
    @Size(min = 8, message = "The password must be at least 8 characters long")
    @Pattern(
            regexp = RegexConstants.PASSWORD_REGEX,
            message = "The password must contain one uppercase letter, one lowercase letter, one number and one special character"
    )
    private String  password;
}
