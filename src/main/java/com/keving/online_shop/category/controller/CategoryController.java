package com.keving.online_shop.category.controller;

import com.keving.online_shop.category.dto.CategoryCreateDTO;
import com.keving.online_shop.category.dto.CategoryResponseDTO;
import com.keving.online_shop.category.dto.CategoryUpdateDTO;
import com.keving.online_shop.category.model.Category;
import com.keving.online_shop.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryCreateDTO categoryDTO){
        Category category = categoryService.createCategory(categoryDTO.getName(), categoryDTO.getDescription());
        return ResponseEntity.ok(category);
    }

    @GetMapping
    ResponseEntity<List<CategoryResponseDTO>> getCategories(){
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/{id}")
    ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable  Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryUpdateDTO category){
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

}
