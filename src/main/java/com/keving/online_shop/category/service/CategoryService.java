package com.keving.online_shop.category.service;

import com.keving.online_shop.category.dto.CategoryResponseDTO;
import com.keving.online_shop.category.dto.CategoryUpdateDTO;
import com.keving.online_shop.category.model.Category;
import com.keving.online_shop.category.repository.CategoryRepository;
import com.keving.online_shop.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(String name, String description){
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);

        if(categoryRepository.existsByNameIgnoreCase(category.getName().trim())){
            throw new BusinessException("The category name already exists", 400);
        }

        categoryRepository.save(category);

        return category;
    }

    public List<CategoryResponseDTO> getCategories(){
        List<CategoryResponseDTO> categoryList = categoryRepository.findAll().stream().map(category -> {
            CategoryResponseDTO dto = new CategoryResponseDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dto.setDescription(category.getDescription());

            return dto;
        }).toList();

        return categoryList;
    }

    public CategoryResponseDTO getCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new BusinessException("Category not found", 404));
        CategoryResponseDTO categoryDTO = new CategoryResponseDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());

        return categoryDTO;
    }

    public CategoryResponseDTO updateCategory(Long id, CategoryUpdateDTO category){
        Category currentCategory = categoryRepository.findById(id).orElseThrow(() -> new BusinessException("Category not found", 404));

        if(category.getName() != null && !category.getName().isBlank()){
            currentCategory.setName(category.getName());
        }

        if(category.getDescription() != null && !category.getDescription().isBlank()){
            currentCategory.setDescription((category.getDescription()));
        }

        categoryRepository.save(currentCategory);

        CategoryResponseDTO dtoResponse = new CategoryResponseDTO();
        dtoResponse.setId(currentCategory.getId());
        dtoResponse.setName(currentCategory.getName());
        dtoResponse.setDescription(currentCategory.getDescription());

        return dtoResponse;
    }
}
