package com.example.blogrestpoints.services;

import com.example.blogrestpoints.entity.Category;
import com.example.blogrestpoints.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    CategoryDto getCategoryById(Integer categoryId);
    Void deleteCategory(Integer categoryId);
    List<CategoryDto> getAllCategories();
}
