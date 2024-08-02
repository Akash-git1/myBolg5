package com.blogapi5.payload;
import lombok.Data;

import javax.validation.constraints.*;


@Data
public class PostDto {

    private Long id;

    @NotEmpty(message = "title should not be Empty")
    @Size(min=2, message = "title should be atlest 2 characters")
    private String title;

    @NotEmpty(message = "Description should not be Empty")
    @Size(min=4, message = "description should be atlest 2 characters")
    private String description;

    @NotEmpty(message = "content should not be Empty")
    private String content;
}
