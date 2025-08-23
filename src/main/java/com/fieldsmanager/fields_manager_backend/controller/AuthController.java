package com.fieldsmanager.fields_manager_backend.controller;

import com.fieldsmanager.fields_manager_backend.entity.User;
import com.fieldsmanager.fields_manager_backend.repository.UserRepository;
import com.fieldsmanager.fields_manager_backend.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
//
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

        if (!password.equals(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}

