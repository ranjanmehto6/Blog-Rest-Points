package com.example.blogrestpoints.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;


    @NotEmpty(message = "Please input minimum charcter")
    @Size(min = 4)
    private String name;

    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotNull
    private String password;
    @NotNull
    private String about;
}
