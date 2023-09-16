package com.example.blogrestpoints.services;

import com.example.blogrestpoints.entity.Category;
import com.example.blogrestpoints.entity.Post;
import com.example.blogrestpoints.entity.User;
import com.example.blogrestpoints.exception.ResourceNotFoundException;
import com.example.blogrestpoints.payload.PostDto;
import com.example.blogrestpoints.repositry.CategoryRepo;
import com.example.blogrestpoints.repositry.PostRepo;
import com.example.blogrestpoints.repositry.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService{


    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;



    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId).
                orElseThrow((()-> new ResourceNotFoundException("User","user id",userId)));

        Category category = this.categoryRepo.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));


        Post post =  this.modelMapper.map(postDto,Post.class);
        Post post1 = postRepo.save(post);
        post1.setPostImage("deafult.png");
        post1.setAddDate(new Date());
        post1.setUser(user);
        post1.setCategory(category);


        Post savedPost = this.postRepo.save(post1);

        return this.modelMapper.map(savedPost, PostDto.class);

    }

    @Override
    public Post updatePost(PostDto postD, Integer postId) {


        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPosts() {
        return null;
    }

    @Override
    public Post getPostById(Integer PostId) {
        return null;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()
                -> new ResourceNotFoundException("category", "category id", categoryId));

        List<Post> byCategory = this.postRepo.findByCategory(category);
        List<PostDto> collect = byCategory.stream().map(bycat -> this.modelMapper.map(bycat, PostDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
        List<Post> byUser = this.postRepo.findByUser(user);
        List<PostDto> collect = byUser.stream().map(byuser -> this.modelMapper.map(byuser, PostDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Post> searchPost(String keword) {
        return null;
    }
}
