package com.example.blogrestpoints.services;

import com.example.blogrestpoints.entity.Post;
import com.example.blogrestpoints.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId );

    Post updatePost(PostDto postD, Integer postId);

    void deletePost(Integer postId);

    List<Post> getAllPosts();

    Post getPostById(Integer PostId);


    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<Post> searchPost(String keword);



}
