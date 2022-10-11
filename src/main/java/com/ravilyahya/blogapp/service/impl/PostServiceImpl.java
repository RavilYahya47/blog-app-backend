package com.ravilyahya.blogapp.service.impl;

import com.ravilyahya.blogapp.exception.ResourceNotFoundException;
import com.ravilyahya.blogapp.model.Category;
import com.ravilyahya.blogapp.model.Post;
import com.ravilyahya.blogapp.model.User;
import com.ravilyahya.blogapp.payloads.PostDTO;
import com.ravilyahya.blogapp.payloads.PostResponse;
import com.ravilyahya.blogapp.repository.CategoryRepository;
import com.ravilyahya.blogapp.repository.PostRepository;
import com.ravilyahya.blogapp.repository.UserRepository;
import com.ravilyahya.blogapp.service.PostService;
import com.ravilyahya.blogapp.util.DTOConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final DTOConverter dtoConverter;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO, Long userId,Long categoryId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Post","user id",userId));
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "category Id",categoryId));


        postDTO.setImageName("default.png");
        Post post = dtoConverter.postDTOToPost(postDTO);
        post.setCategory(category);
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        return dtoConverter.postToPostDTO(savedPost);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long postId) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
        existingPost.setTitle(postDTO.getTitle());
        existingPost.setContent(postDTO.getContent());
        existingPost.setImageName(postDTO.getImageName());

        PostDTO savedPostDTO = dtoConverter.postToPostDTO(postRepository.save(existingPost));
        return savedPostDTO;
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
        postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort;

        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else {
            sort=Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<Post> page= postRepository.findAll(pageable);
        List<Post> posts = page.getContent();
        List<PostDTO> postDTOS = posts.stream().map(dtoConverter::postToPostDTO).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOS);
        postResponse.setPageNumber(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalElements(page.getTotalElements());
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setLastPage(page.isLast());

        return postResponse;
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostDTO postDTO = dtoConverter.postToPostDTO(
                postRepository.findById(postId)
                        .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId)));
        return postDTO;
    }

    @Override
    public List<PostDTO> getPostsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        List<PostDTO> postDTOS = this.postRepository.findByCategory(category).stream().map(dtoConverter::postToPostDTO).toList();
        return postDTOS;
    }

    @Override
    public List<PostDTO> getPostsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
        List<PostDTO> postDTOS = this.postRepository.findByUser(user)
                .stream().map(dtoConverter::postToPostDTO).toList();
        return postDTOS;
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        return null;
    }
}
