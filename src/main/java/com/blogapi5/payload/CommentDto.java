package com.blogapi5.payload;
import lombok.*;
@Data
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
}
