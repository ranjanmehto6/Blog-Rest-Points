package com.example.blogrestpoints.controller;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthRequest {

    private String userName;
    private String password;
}
