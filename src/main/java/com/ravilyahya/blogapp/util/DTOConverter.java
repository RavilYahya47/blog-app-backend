package com.ravilyahya.blogapp.util;

import com.ravilyahya.blogapp.model.Category;
import com.ravilyahya.blogapp.model.Comment;
import com.ravilyahya.blogapp.model.Post;
import com.ravilyahya.blogapp.model.User;
import com.ravilyahya.blogapp.payloads.CategoryDTO;
import com.ravilyahya.blogapp.payloads.CommentDTO;
import com.ravilyahya.blogapp.payloads.PostDTO;
import com.ravilyahya.blogapp.payloads.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DTOConverter {


    private final ModelMapper modelMapper;



    public UserDTO userToUserDTO(User user){
        return this.modelMapper.map(user, UserDTO.class);
    }

    public User userDTOToUser(UserDTO userDTO){
        return this.modelMapper.map(userDTO,User.class);
    }

    public Category categoryDTOToCategory(CategoryDTO categoryDTO){
        return this.modelMapper.map(categoryDTO,Category.class);
    }

    public CategoryDTO categoryToCategoryDTO(Category category){
        return this.modelMapper.map(category,CategoryDTO.class);
    }

    public Post postDTOToPost(PostDTO postDTO){
        return this.modelMapper.map(postDTO, Post.class);
    }

    public PostDTO postToPostDTO(Post post){
        return this.modelMapper.map(post,PostDTO.class);
    }

    public Comment commentDTOToComment(CommentDTO commentDTO){
        return this.modelMapper.map(commentDTO, Comment.class);
    }

    public CommentDTO commentToCommentDTO(Comment comment){
        return modelMapper.map(comment,CommentDTO.class);
    }
}
