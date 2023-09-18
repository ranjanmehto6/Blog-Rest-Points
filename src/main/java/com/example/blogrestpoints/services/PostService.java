package com.example.blogrestpoints.services;

import com.example.blogrestpoints.entity.Post;
import com.example.blogrestpoints.payload.PostDto;
import com.example.blogrestpoints.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId );

    PostDto updatePost(PostDto postD, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPosts(Integer pageNo, Integer pageSize);

    PostDto getPostById(Integer PostId);


    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyword);



}
