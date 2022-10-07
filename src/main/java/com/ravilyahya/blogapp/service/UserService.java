package com.ravilyahya.blogapp.service;

import com.ravilyahya.blogapp.model.User;
import com.ravilyahya.blogapp.payloads.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user,Long userId);
    UserDTO getUserById(Long userId);
    List<UserDTO> getAllUsers();
    void deleteUser(Long userId);



}
