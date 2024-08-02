package com.blogapi5.service;

import com.blogapi5.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(Long postId);

    void deleteCommentById(Long pId, long id);
//    public CommentDto upadteByCommentId(long id, CommentDto commentDto);

    CommentDto updateCommentById(long pId, long id, CommentDto commentDto);

    CommentDto getCommentById(Long pId, Long id);
// public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest);

}
