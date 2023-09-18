package com.example.blogrestpoints.payload;

import com.example.blogrestpoints.entity.Category;
import com.example.blogrestpoints.entity.Comment;
import com.example.blogrestpoints.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
public class PostDto {

    private Integer postId;


    private String postTitle;
    private String postContent;
    private String postImage;
    private Date addDate;

    private CategoryDto category;
    private UserDto user;

    private Set<Comment> comments = new HashSet<Comment>();


}
