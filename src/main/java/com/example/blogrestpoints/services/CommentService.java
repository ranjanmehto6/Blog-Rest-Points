package com.example.blogrestpoints.services;

import com.example.blogrestpoints.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);
    void deleteComment(Integer commentId);

    List<CommentDto> getAllComments();

}
