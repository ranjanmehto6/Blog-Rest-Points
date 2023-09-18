package com.example.blogrestpoints.services;

import com.example.blogrestpoints.entity.User;
import com.example.blogrestpoints.exception.ResourceNotFoundException;
import com.example.blogrestpoints.payload.UserDto;
import com.example.blogrestpoints.payload.UserResponse;
import com.example.blogrestpoints.repositry.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public UserResponse getAllUsers(Integer pageNo, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<User> page = this.userRepo.findAll(pageable);

        List<User> users = page.getContent();
        List<UserDto> collect = users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        UserResponse userResponse = new UserResponse();
        userResponse.setContent(collect);
        userResponse.setPageNo(page.getNumber());
        userResponse.setPageSize(page.getSize());
        userResponse.setTotalPages(page.getTotalPages());
        userResponse.setTotalElements(page.getTotalElements());

        return userResponse;

    }
}




//Rference Code PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);