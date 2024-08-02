package com.blogapi5.payload;
import lombok.*;

@Data
public class LoginDto {

    private String usernameOrEmail;
    private String password ;
}


