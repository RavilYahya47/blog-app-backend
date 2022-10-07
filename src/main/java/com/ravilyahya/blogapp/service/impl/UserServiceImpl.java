package com.ravilyahya.blogapp.service.impl;

import com.ravilyahya.blogapp.exception.ResourceNotFoundException;
import com.ravilyahya.blogapp.model.User;
import com.ravilyahya.blogapp.payloads.UserDTO;
import com.ravilyahya.blogapp.repository.UserRepository;
import com.ravilyahya.blogapp.service.UserService;
import com.ravilyahya.blogapp.util.DTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO user) {
        User savedUser =  userRepository.save(DTOConverter.userDTOToUser(user));
        return DTOConverter.userToUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long userId) {
        User user =userRepository.findById(userId)
                .orElseThrow(() ->new ResourceNotFoundException("User","id",userId));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());
        user.setPassword(userDTO.getPassword());

        User updatedUser = userRepository.save(user);


        return DTOConverter.userToUserDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return DTOConverter.userToUserDTO(
                userRepository.findById(userId)
                .orElseThrow(() ->new ResourceNotFoundException("User","id",userId))
        );
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = users.stream().map(DTOConverter::userToUserDTO).toList();
        return usersDTO;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->new ResourceNotFoundException("User","id",userId));
        userRepository.delete(user);

    }
}
