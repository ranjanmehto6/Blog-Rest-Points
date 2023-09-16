package com.example.blogrestpoints.repositry;

import com.example.blogrestpoints.entity.Category;
import com.example.blogrestpoints.entity.Post;
import com.example.blogrestpoints.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
