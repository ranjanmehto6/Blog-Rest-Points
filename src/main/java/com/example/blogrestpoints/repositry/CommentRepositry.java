package com.example.blogrestpoints.repositry;

import com.example.blogrestpoints.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositry extends JpaRepository<Comment, Integer> {
}
