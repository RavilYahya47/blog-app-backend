package com.ravilyahya.blogapp.service.impl;

import com.ravilyahya.blogapp.config.AppConstraints;
import com.ravilyahya.blogapp.exception.ResourceNotFoundException;
import com.ravilyahya.blogapp.model.Role;
import com.ravilyahya.blogapp.model.User;
import com.ravilyahya.blogapp.payloads.UserDTO;
import com.ravilyahya.blogapp.repository.RoleRepository;
import com.ravilyahya.blogapp.repository.UserRepository;
import com.ravilyahya.blogapp.service.UserService;
import com.ravilyahya.blogapp.util.DTOConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DTOConverter dtoConverter;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserDTO registerNewUser(UserDTO userDTO) {
        User user = dtoConverter.userDTOToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findById(AppConstraints.NORMAL).get();
        user.getRoles().add(role);
        User savedUser = userRepository.save(user);
        return dtoConverter.userToUserDTO(savedUser);
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        User savedUser =  userRepository.save(dtoConverter.userDTOToUser(user));
        return dtoConverter.userToUserDTO(savedUser);
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


        return dtoConverter.userToUserDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return dtoConverter.userToUserDTO(
                userRepository.findById(userId)
                .orElseThrow(() ->new ResourceNotFoundException("User","id",userId))
        );
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = users.stream().map(dtoConverter::userToUserDTO).toList();
        return usersDTO;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->new ResourceNotFoundException("User","id",userId));
        userRepository.delete(user);

    }
}
