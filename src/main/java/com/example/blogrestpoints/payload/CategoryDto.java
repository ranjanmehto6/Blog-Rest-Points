package com.example.blogrestpoints.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {


    private Integer categoryId;

    @NotEmpty
    @Size(min = 4,max = 100)
    private String categoryTitle;
    @NotEmpty
    @Size(min = 1,max = 200)
    private String categoryDescription;
}
