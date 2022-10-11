package com.ravilyahya.blogapp.controller;

import com.ravilyahya.blogapp.config.AppConstraints;
import com.ravilyahya.blogapp.payloads.ApiResponse;
import com.ravilyahya.blogapp.payloads.PostDTO;
import com.ravilyahya.blogapp.payloads.PostResponse;
import com.ravilyahya.blogapp.service.FileService;
import com.ravilyahya.blogapp.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    private final FileService fileService;




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
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable(name = "userId") Long userId) {
        List<PostDTO> postDTOS = postService.getPostsByUser(userId);
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    // get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable(name = "categoryId") Long categoryId) {
        List<PostDTO> postDTOS = postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstraints.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstraints.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstraints.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstraints.SORT_DIR, required = false) String sortDir
    ) {
        PostResponse postResponse = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    //get post by postid
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "postId") Long postId) {
        PostDTO postDTO = postService.getPostById(postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePostById(@PathVariable(name = "postId") Long postId) {
        postService.deletePost(postId);
        return new ApiResponse("Post successfully deleted!", true);
    }

    //update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable(name = "postId") Long postId) {
        PostDTO updatedPostDTO = postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(updatedPostDTO, HttpStatus.OK);
    }

    //search
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable(name = "keyword") String keyword) {
        List<PostDTO> postDTOS = postService.searchPosts(keyword);
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    //post image upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadPostImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable Long postId) throws IOException {
        String filename = fileService.uploadImage(AppConstraints.path, image);
        PostDTO postDTO = postService.getPostById(postId);
        postDTO.setImageName(filename);
        PostDTO updatedPostDTO = postService.updatePost(postDTO, postId);

        return new ResponseEntity<>(updatedPostDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable String imageName,
            HttpServletResponse response) throws IOException {
        InputStream inputStream = fileService.getResource(AppConstraints.path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream,response.getOutputStream());
    }
}
