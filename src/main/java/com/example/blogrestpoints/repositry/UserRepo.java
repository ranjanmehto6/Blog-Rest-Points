package com.example.blogrestpoints.repositry;

import com.example.blogrestpoints.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {


    Optional<User> findByEmail(String email);
}
