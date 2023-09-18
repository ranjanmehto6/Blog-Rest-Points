package com.example.blogrestpoints.services;

import com.example.blogrestpoints.payload.PostResponse;
import com.example.blogrestpoints.payload.UserDto;
import com.example.blogrestpoints.payload.UserResponse;

import java.util.List;

public interface UserService {


    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer id);
    void deleteUser(Integer id);
    UserDto getUserById(Integer id);
    UserResponse getAllUsers(Integer pageNo, Integer pageSize);

}
