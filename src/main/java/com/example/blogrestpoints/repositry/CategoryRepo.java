package com.example.blogrestpoints.repositry;

import com.example.blogrestpoints.entity.Category;
import com.example.blogrestpoints.payload.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
