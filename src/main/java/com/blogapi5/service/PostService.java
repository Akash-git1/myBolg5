package com.blogapi5.service;

import com.blogapi5.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> findAllPost(int pageNo, int pageSize, String sortBy,String sortDir);


    PostDto getPostById(long id);

    PostDto updatePostById(long id, PostDto postDto);

    void deletePostById(long id);
}
