package com.example.blogrestpoints.security;


import com.example.blogrestpoints.entity.User;
import com.example.blogrestpoints.exception.ResourceNotFoundException;
import com.example.blogrestpoints.repositry.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("user", "username", 0));


        return user;
        //Loading user from database
    }
}
