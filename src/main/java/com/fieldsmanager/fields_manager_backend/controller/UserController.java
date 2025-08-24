package com.fieldsmanager.fields_manager_backend.controller;
import com.fieldsmanager.fields_manager_backend.dto.SignupRequest;
import com.fieldsmanager.fields_manager_backend.entity.User;
import com.fieldsmanager.fields_manager_backend.repository.UserRepository;
import com.fieldsmanager.fields_manager_backend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

}

