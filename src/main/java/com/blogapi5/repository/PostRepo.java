package com.blogapi5.repository;

import com.blogapi5.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Long> {
}
