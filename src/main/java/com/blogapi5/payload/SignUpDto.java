package com.blogapi5.payload;

import lombok.*;


@Data
public class SignUpDto {

    private String name;
    private String username;
    private String email;
    private String password;

}
