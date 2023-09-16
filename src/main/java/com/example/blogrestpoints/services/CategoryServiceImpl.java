package com.example.blogrestpoints.services;

import com.example.blogrestpoints.entity.Category;
import com.example.blogrestpoints.exception.ResourceNotFoundException;
import com.example.blogrestpoints.payload.CategoryDto;
import com.example.blogrestpoints.repositry.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = this.modelMapper.map(categoryDto, Category.class);

        Category save = this.categoryRepo.save(category);

        return this.modelMapper.map(save, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(category.getCategoryDescription());

        return this.modelMapper.map(category,CategoryDto.class);



    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public Void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
        categoryRepo.delete(category);
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> all = this.categoryRepo.findAll();
        List<CategoryDto> collect = all.stream().map((category ->
                this.modelMapper.map(category, CategoryDto.class))).collect(Collectors.toList());
        return collect;
    }
}
