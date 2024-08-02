package com.blogapi5.controller;

import com.blogapi5.payload.CommentDto;
import com.blogapi5.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value="postId") long postId, @RequestBody CommentDto commentDto){
        CommentDto createdComment = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
//        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);

    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable Long postId){
        List<CommentDto> commentDto = commentService.getCommentsByPostId(postId);
        return commentDto;
    }

    @DeleteMapping("/posts/{pId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("id") long id,@PathVariable("pId") long pId){
        commentService.deleteCommentById(pId,id);
        return new ResponseEntity<>("Post Id Is deleted By Comments:"+id+"By Posts:"+pId, HttpStatus.OK);
    }

    @PutMapping("/posts/{pId}/comments/{id}")
    public CommentDto updateCommentById(@PathVariable(value = "pId") Long pId,@PathVariable(value = "id") long id,@RequestBody CommentDto commentDto){
        CommentDto updated  = commentService.updateCommentById(pId,id,commentDto);
        return updated;
    }
    @GetMapping("/posts/{postId}/comments/{id}")
   public  ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") Long pId,@PathVariable("id") Long id){
        CommentDto commentById = commentService.getCommentById(pId, id);
        return ResponseEntity.ok(commentById);
    }




}
