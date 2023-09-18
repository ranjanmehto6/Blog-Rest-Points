package com.example.blogrestpoints.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PostResponse {

  private List<PostDto> content;
  private int pageNo;
  private int pageSize;
  private long totalElements;
  private int totalPages;

}
