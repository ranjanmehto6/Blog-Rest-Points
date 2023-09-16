package com.example.blogrestpoints.services;

import com.example.blogrestpoints.entity.User;
import com.example.blogrestpoints.exception.ResourceNotFoundException;
import com.example.blogrestpoints.payload.UserDto;
import com.example.blogrestpoints.repositry.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public UserDto createUser(UserDto userDto) {

        User user = modelMapper.map(userDto,User.class);
        userRepo.save(user);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User user = userRepo.findById(id).orElseThrow
                ((()-> new ResourceNotFoundException("User","User Id",id)));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        this.userRepo.save(user);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepo.findById(id).orElseThrow
                ((()-> new ResourceNotFoundException("User","User Id",id)));
        userRepo.delete(user);


    }


    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepo.findById(id).orElseThrow
                ((()-> new ResourceNotFoundException("User","User Id",id)));

        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> collect = users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return collect;

    }
}




//Rference Code PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);