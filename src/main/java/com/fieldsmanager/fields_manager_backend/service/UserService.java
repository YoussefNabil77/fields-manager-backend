package com.fieldsmanager.fields_manager_backend.service;

import com.fieldsmanager.fields_manager_backend.repository.UserRepository;

import com.fieldsmanager.fields_manager_backend.entity.User;

import org.springframework.security.crypto.password.PasswordEncoder;



public class UserService {

    private UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;

    public User registerUser(String name, String email, String phone, String rawPassword) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already registered");
        }
        if (userRepository.existsByPhone(phone)) {
            throw new RuntimeException("Phone already registered");
        }

        User user = User.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .password(passwordEncoder.encode(rawPassword))
                .role("PLAYER")  // default role
                .status("ACTIVE") // default status
                .build();

        return userRepository.save(user);
    }

}
