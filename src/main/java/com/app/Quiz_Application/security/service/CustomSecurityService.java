package com.app.Quiz_Application.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.Quiz_Application.user.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomSecurityService implements UserDetailsService {

    private Map<String,User> users = new HashMap<>();
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.get(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    public void registerUser(String username, String password, String email, String role) throws Exception{
        if(users.containsKey(username)){
            throw new Exception("User already exists");
        }

        String passwordEncodded = passwordEncoder.encode(password);
        User user = new User(username,passwordEncodded,email,role);

        users.put(username, user);
    }

}