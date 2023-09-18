package com.example.blogrestpoints.controller;


import com.example.blogrestpoints.payload.CommentDto;
import com.example.blogrestpoints.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,

                                                  @PathVariable("postId")  Integer postId) throws Exception {

        CommentDto comment = this.commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(comment, HttpStatus.OK);


    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Integer commentId) throws Exception {

        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> getAllComments() throws Exception {

       List <CommentDto> allComments = this.commentService.getAllComments();
        return new ResponseEntity<>(allComments,HttpStatus.OK);

    }





}
