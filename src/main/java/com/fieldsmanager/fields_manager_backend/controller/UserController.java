package com.fieldsmanager.fields_manager_backend.controller;
import com.fieldsmanager.fields_manager_backend.dto.ResetPasswordConfirmRequest;
import com.fieldsmanager.fields_manager_backend.dto.ResetPasswordRequest;
import com.fieldsmanager.fields_manager_backend.dto.SignupRequest;
import com.fieldsmanager.fields_manager_backend.dto.UpdateProfileRequest;
import com.fieldsmanager.fields_manager_backend.entity.User;
import com.fieldsmanager.fields_manager_backend.repository.UserRepository;
import com.fieldsmanager.fields_manager_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("auth/api/users")

public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    public UserController(UserService userService , UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest request) {
        return userService.registerUser(
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                request.getPassword()
        );
    }
    @GetMapping("/me")
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // comes from JWT subject

        return userRepository.findByEmail(email)  // <-- instance call
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    // Request Password Reset
    @PostMapping("/reset-password/request")
    public String requestPasswordReset(@RequestBody ResetPasswordRequest request) {
        return userService.requestReset(request.getEmail());
    }

    // Confirm Password Reset
    @PostMapping("/reset-password/confirm")
    public String confirmPasswordReset(@RequestBody ResetPasswordConfirmRequest request) {
        userService.confirmReset(
                request.getEmail(),
                request.getCode(),
                request.getNewPassword()
        );
        return "Password updated successfully!";
    }



    @PutMapping("/update")
    public User updateUser(@RequestBody User updatedUser) {
        User existingUser = userRepository.findByEmail(updatedUser.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));

        existingUser.setName(updatedUser.getName());
        existingUser.setPassword(updatedUser.getPassword());

        return userRepository.save(existingUser);
    }

}

