package com.modelmapper.map.service;



import com.modelmapper.map.entity.Post;
import com.modelmapper.map.exception.ResourceNotFoundException;
import com.modelmapper.map.payload.PostDTO;
import com.modelmapper.map.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepo;
    private ModelMapper mapper;

    public PostService(PostRepository postRepo, ModelMapper mapper) {
        this.postRepo = postRepo;
        this.mapper = mapper;
    }

    public PostService(PostRepository postRepo) {
        this.postRepo = postRepo;
        this.mapper = new ModelMapper();
    }

    public PostService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PostService() {
        this.mapper = new ModelMapper();
    }


    public PostDTO createPost(PostDTO postDto) {
        String postId= UUID.randomUUID().toString();
        postDto.setId(postId);
        Post post=mapToEntity(postDto);

//     Post post =new Post();
//       post.setTitle(postDto.getTitle());
//       post.setDescription(postDto.getDescription());
//       post.setContent(postDto.getContent());
        Post newPost=postRepo.save(post);
        PostDTO dto=mapToDto(newPost);
//       PostDTO dto =new PostDTO();
//       dto.setId(newPost.getId());
//       dto.setTitle(newPost.getTitle());
//       dto.setDescription(newPost.getDescription());
//       dto.setContent(newPost.getContent());
        return dto;
    }


    public List<PostDTO> listAllPosts() {

        List<Post> posts = postRepo.findAll();

        List<PostDTO>postDtos=posts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    public PostDTO getPostById(String id) {
        Post newPost = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found:" + id));
        PostDTO postDto=mapToDto(newPost);
        return postDto;
    }

    public PostDTO updatePost(PostDTO postDto, String id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found:" + id));
        Post newPost= mapToEntity(postDto);
//        newPost.setId(id);
        Post updatedPost =postRepo.save(newPost);
        PostDTO dto= mapToDto(updatedPost);
        return dto;
    }

    public void deletePostById(String id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found:" + id));
        postRepo.deleteById(id);
    }
    private PostDTO mapToDto(Post post) {
        PostDTO postdto =mapper.map(post,PostDTO.class);
//        PostDTO dto =new PostDTO();
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());
        return postdto;
    }
    private Post mapToEntity(PostDTO postDto) {
        Post post=mapper.map(postDto,Post.class);
//        Post post=new Post();
//        post.setId(postDto.getId());
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }
}

