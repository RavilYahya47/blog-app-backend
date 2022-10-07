package com.ravilyahya.blogapp.util;

import com.ravilyahya.blogapp.model.User;
import com.ravilyahya.blogapp.payloads.UserDTO;

public class DTOConverter {

    public static UserDTO userToUserDTO(User user){
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .about(user.getAbout())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();

        return userDTO;
    }

    public static User userDTOToUser(UserDTO userDTO){


        User user = User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .about(userDTO.getAbout())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .build();

        return user;
    }
}
