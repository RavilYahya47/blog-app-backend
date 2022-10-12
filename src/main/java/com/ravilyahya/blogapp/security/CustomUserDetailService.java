package com.ravilyahya.blogapp.security;

import com.ravilyahya.blogapp.exception.ResourceNotFoundException;
import com.ravilyahya.blogapp.model.User;
import com.ravilyahya.blogapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        //loading user from database by username

        return userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "email " + username, 0L));
    }
}