package com.keving.online_shop.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryUpdateDTO {
    private String name;
    private String description;
}
