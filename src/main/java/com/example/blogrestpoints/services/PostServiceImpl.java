package com.example.blogrestpoints.services;

import com.example.blogrestpoints.entity.Category;
import com.example.blogrestpoints.entity.Post;
import com.example.blogrestpoints.entity.User;
import com.example.blogrestpoints.exception.ResourceNotFoundException;
import com.example.blogrestpoints.payload.PostDto;
import com.example.blogrestpoints.payload.PostResponse;
import com.example.blogrestpoints.repositry.CategoryRepo;
import com.example.blogrestpoints.repositry.PostRepo;
import com.example.blogrestpoints.repositry.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post_id", postId));
        post.setPostContent(postDto.getPostContent());
        post.setPostTitle(postDto.getPostTitle());
        post.setPostImage(postDto.getPostImage());

        Post save = this.postRepo.save(post);
        PostDto map = this.modelMapper.map(save, PostDto.class);


        return map;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post_id", postId));
        postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPosts(Integer pageNo, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNo,pageSize);

        Page<Post> pagePost = this.postRepo.findAll(pageable);

        List<Post> posts = pagePost.getContent();
        List<PostDto> collect = posts.stream().map(bycat -> this.modelMapper.map(bycat, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(collect);
        postResponse.setPageNo(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer PostId) {
        Post post = this.postRepo.findById(PostId).orElseThrow(() -> new ResourceNotFoundException("Post", "post_id", PostId));
        PostDto map = this.modelMapper.map(post, PostDto.class);
        return map;
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
    public List<PostDto> searchPost(String keyword) {

        List<Post> posts = this.postRepo.findByPostTitleContaining(keyword);

        List<PostDto> collect = posts.stream().map(e -> this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());


        return collect;
    }
}
