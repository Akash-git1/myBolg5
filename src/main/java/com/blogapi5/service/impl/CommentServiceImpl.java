package com.blogapi5.security.service.Impl;

import com.blogapi5.entity.Comment;
import com.blogapi5.entity.Post;
import com.blogapi5.exception.ResourceNotFoundException;
import com.blogapi5.payload.CommentDto;
import com.blogapi5.repository.CommentRepository;
import com.blogapi5.repository.PostRepo;
import com.blogapi5.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepo postRepo;
    private ModelMapper modelMapper;



    public CommentServiceImpl(PostRepo postRepo,ModelMapper modelMapper,CommentRepository comRepo) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
        this.commentRepository = comRepo;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(postId)
        );
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);

        Comment save = commentRepository.save(comment);

        CommentDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        postRepo.findById(postId).orElseThrow( //
                ()-> new ResourceNotFoundException(postId)
        );
        List<Comment> comments = commentRepository.findByPostId(postId);

       return comments.stream()
                      .map(this::mapToDto)
                      .collect(Collectors.toList());
    }

    @Override
    public void deleteCommentById(Long pId, long id) {
        Post post = postRepo.findById(pId).orElseThrow(
                () -> new ResourceNotFoundException(pId)
        );
        Comment comment = commentRepository.findById(id).orElseThrow( // id-comment , post_id-post
                () -> new ResourceNotFoundException(id)
        );

        commentRepository.deleteById(id);
    }

    @Override
    public CommentDto updateCommentById(long pId,long id,CommentDto commentDto) {
        Post post = postRepo.findById(pId).orElseThrow(
                () -> new ResourceNotFoundException(pId)
        );
        Comment comment = commentRepository.findById(id).orElseThrow( // id-comment , post_id-post
                () -> new ResourceNotFoundException(id)
        );

        Comment comment1 = mapToEntity(commentDto);
        comment1.setId(comment.getId());
        comment1.setPost(post);

        Comment save = commentRepository.save(comment1);
        return mapToDto(save);
    }

    @Override
    public CommentDto getCommentById(Long pId, Long id){
        Post post = postRepo.findById(pId).orElseThrow(
                () -> new ResourceNotFoundException(pId)
        );
        Comment comment = commentRepository.findById(id).orElseThrow( // id-comment , post_id-post
                () -> new ResourceNotFoundException(id)
        );
        return mapToDto(comment);
    }

    Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }

    CommentDto mapToDto(Comment comment){
        CommentDto commentdto = modelMapper.map(comment, CommentDto.class);
        return commentdto;
    }

//    @Override
//    public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest) {
//        // retrieve post entity by id
//        Post post = postRepo.findById(postId).orElseThrow(
//                () -> new ResourceNotFoundException(postId));
//
//        // retrieve comment by id
//        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
//                new ResourceNotFoundException(commentId));
//
//        comment.setName(commentRequest.getName());
//        comment.setEmail(commentRequest.getEmail());
//        comment.setBody(commentRequest.getBody());
//        comment.setPost(post);
//
//        Comment updatedComment = commentRepository.save(comment);
//        return mapToDto(updatedComment);
//    }

}
