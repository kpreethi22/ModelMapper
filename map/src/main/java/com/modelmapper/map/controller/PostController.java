package com.modelmapper.map.controller;




import com.modelmapper.map.payload.PostDTO;
import com.modelmapper.map.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private PostService postService;
    public PostController(PostService postService) {

        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDto) {
        PostDTO dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @GetMapping
    public  List<PostDTO> listAllPosts(){
        return postService.listAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") String id) {
        PostDTO dto = postService.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto, @PathVariable(name = "id") String id) {
        PostDTO postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") String id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("post is deleted",HttpStatus.OK);
    }
}