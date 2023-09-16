package com.example.blogrestpoints.repositry;

import com.example.blogrestpoints.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
