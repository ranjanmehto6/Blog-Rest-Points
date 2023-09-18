package com.example.blogrestpoints.services;

import com.example.blogrestpoints.entity.Comment;
import com.example.blogrestpoints.entity.Post;
import com.example.blogrestpoints.exception.ResourceNotFoundException;
import com.example.blogrestpoints.payload.CommentDto;
import com.example.blogrestpoints.repositry.CommentRepositry;
import com.example.blogrestpoints.repositry.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepositry commentRepositry;

    @Autowired
    private PostRepo postRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        Comment comment =  this.mapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment save = this.commentRepositry.save(comment);
        return this.mapper.map(comment,CommentDto.class);


    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepositry.findById(commentId).get();
        this.commentRepositry.delete(comment);
        return;

    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> all = this.commentRepositry.findAll();
        List<CommentDto> collect = all.stream().map(e -> this.mapper.map(e,CommentDto.class)).collect(Collectors.toList());
        return collect;
    }
}
