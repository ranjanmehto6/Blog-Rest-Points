package com.example.blogrestpoints.controller;


import com.example.blogrestpoints.entity.Post;
import com.example.blogrestpoints.payload.PostDto;
import com.example.blogrestpoints.payload.PostResponse;
import com.example.blogrestpoints.services.FileService;
import com.example.blogrestpoints.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;


    @Autowired
    private FileService fileService;


    @PostMapping("/users/{userId}/categories/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId) {

        PostDto createdPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/categories/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
        List<PostDto> posts = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDto> posts = postService.getPostByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize
            )
            {
      PostResponse allPosts = postService.getAllPosts(pageNo,pageSize);
        return new ResponseEntity<PostResponse>(allPosts,HttpStatus.OK);
    }



    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> getPostBySearch(@PathVariable String keyword){
        List<PostDto> postDtos = this.postService.searchPost(keyword);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }



    @Value("${project.image}")
    private String path;

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
    ) throws IOException {

        PostDto postById = this.postService.getPostById(postId);
        String s = this.fileService.uploadImage(path, image);
        postById.setPostImage(s);
        PostDto updatePost = this.postService.updatePost(postById,postId);
        return new ResponseEntity<>(updatePost,HttpStatus.OK);


    }




    @GetMapping(value = "/post/image/{imageName}",produces =MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName, HttpServletResponse response
    ) throws IOException
    {
        InputStream  resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
