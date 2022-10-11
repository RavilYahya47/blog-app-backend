package com.ravilyahya.blogapp.controller;

import com.ravilyahya.blogapp.payloads.ApiResponse;
import com.ravilyahya.blogapp.payloads.PostDTO;
import com.ravilyahya.blogapp.payloads.PostResponse;
import com.ravilyahya.blogapp.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PostController {
    private final PostService postService;


    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(
            @RequestBody PostDTO postDTO,
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "categoryId") Long categoryId) {

        PostDTO savedPost = postService.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable(name = "userId") Long userId){
        List<PostDTO> postDTOS = postService.getPostsByUser(userId);
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    // get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable(name = "categoryId") Long categoryId){
        List<PostDTO> postDTOS = postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam (value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam (value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
            @RequestParam (value = "sortBy",defaultValue = "postId",required = false) String sortBy,
            @RequestParam (value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        PostResponse postResponse = postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    //get post by postid
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "postId") Long postId){
        PostDTO postDTO = postService.getPostById(postId);
        return new ResponseEntity<>(postDTO,HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePostById(@PathVariable(name = "postId") Long postId){
        postService.deletePost(postId);
        return new ApiResponse("Post successfully deleted!",true);
    }

    //update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable(name = "postId") Long postId){
        PostDTO updatedPostDTO = postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(updatedPostDTO,HttpStatus.OK);
    }

    //search
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable(name = "keyword") String keyword){
        List<PostDTO> postDTOS = postService.searchPosts(keyword);
        return new ResponseEntity<>(postDTOS,HttpStatus.OK);
    }
}
