package com.blogapi5.controller;

import com.blogapi5.payload.PostDto;
import com.blogapi5.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;
    PostController(PostService postSer){
        this.postService = postSer;
    }


//http://localhost:8080/api/posts
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
  public ResponseEntity<?> createPost(@RequestBody PostDto postDto, @Valid BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

         return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);//201
    }

    // http://localhost:8080/api/posts?pageNo=1&pageSize=3
    @GetMapping
    public List<PostDto> getAllPost(    @RequestParam(value="pageNo", defaultValue="0", required = false ) int pageNo  ,
                                        @RequestParam(value="pageSize", defaultValue="5", required = false) int pageSize,
                                        @RequestParam(value="sortBy", defaultValue="id", required = false) String sortBy,
                                        @RequestParam(value="sortDir", defaultValue="asc", required = false) String sortDir){

        List<PostDto> allPost = postService.findAllPost(pageNo,pageSize,sortBy,sortDir);
        return allPost;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        PostDto postById = postService.getPostById(id);
        return new ResponseEntity<>(postById, HttpStatus.OK); // 200
    }

    @PreAuthorize("hasRole('ADMIN')")
     @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") long id,
                                              @RequestBody PostDto postDto){
        PostDto dto =postService.updatePostById(id, postDto);
         return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
         postService.deletePostById(id );
        return new ResponseEntity<>("Post Id Is deleted:"+id, HttpStatus.OK);
    }

}
