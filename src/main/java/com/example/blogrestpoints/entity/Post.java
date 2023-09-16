package com.example.blogrestpoints.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "posts")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;


    private String postTitle;
    private String postContent;
    private String postImage;
    private Date addDate;


    @ManyToOne()
    private Category category;

    @ManyToOne
    private User user;



}
