package com.example.blogrestpoints.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    String resourceName;
    String fieldName;
    Integer fieldId;

    public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldId){

        super(String.format("%s not found with %s : %d", resourceName, fieldName, fieldId));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldId=fieldId;
    }
}
