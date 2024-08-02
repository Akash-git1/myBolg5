package com.blogapi5.exception;


import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException {
    private HttpStatus httpStatus;

    public BlogAPIException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
