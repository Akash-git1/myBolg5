package com.blogapi5.repository;

import com.blogapi5.entity.Comment;
import com.blogapi5.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Long postId);
}
