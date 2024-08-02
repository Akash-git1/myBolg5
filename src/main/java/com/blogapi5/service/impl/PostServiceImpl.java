package com.blogapi5.security.service.Impl;

import com.blogapi5.entity.Post;
import com.blogapi5.exception.ResourceNotFoundException;
import com.blogapi5.payload.PostDto;
import com.blogapi5.repository.PostRepo;
import com.blogapi5.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepo postRepo;
    private ModelMapper modelMapper;

    PostServiceImpl(PostRepo postRepo,ModelMapper modelMapper){
        this.postRepo = postRepo;
        this.modelMapper=modelMapper;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToPost(postDto);
        Post save = postRepo.save(post);
        return  mapToDto(save);

    }

    @Override
    public List<PostDto> findAllPost(int pageNo, int pageSize, String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> all = postRepo.findAll(pageable);
        List<Post> content = all.getContent();
        List<PostDto> collect = content.stream().map(post -> mapToDto(post)).collect(Collectors.toList()); //map,filter,sort
        return collect;
    }

    @Override
    public PostDto getPostById(long id) {
//        Optional<Post> byId = postRepo.findById(id);
//        Post post1 = byId.get();//1
//
//        Post post = postRepo.findById(id).orElseThrow();//2

        Post post =   postRepo.findById(id).orElseThrow( //3
                ()-> new ResourceNotFoundException(id)
        );
        PostDto dto = mapToDto(post);
        return dto;
    }

    @Override
    public PostDto updatePostById(long id, PostDto postDto) {
        Post post =   postRepo.findById(id).orElseThrow( //3
                ()-> new ResourceNotFoundException(id)
        );

        Post post1 = mapToPost(postDto);
        post1.setId(post.getId());
        Post save = postRepo.save(post1);

         return   mapToDto(save);


//        Post save = postRepo.save(post);
//        post.setId(save.getId());
//
//        PostDto dto = mapToDto(save);
//        return dto;
    }

    @Override
    public void deletePostById(long id) {
        postRepo.deleteById(id);

    }

    Post mapToPost(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }

    PostDto mapToDto(Post post){
        PostDto dto = modelMapper.map(post, PostDto.class);
//        PostDto dto = new PostDto();
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());
        return dto;
    }
}
