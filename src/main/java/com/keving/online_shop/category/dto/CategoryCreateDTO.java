package com.keving.online_shop.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryCreateDTO {
    @NotBlank(message = "The category is required")
    @Size(max = 50, message = "The name must be less than 50 characters")
    private String name;

    @Size(max = 200, message = "The description must not be greater than 200 characters")
    private String description;
}
