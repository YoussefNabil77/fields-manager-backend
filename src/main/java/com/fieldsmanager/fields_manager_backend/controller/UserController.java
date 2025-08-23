package com.fieldsmanager.fields_manager_backend.controller;
import com.fieldsmanager.fields_manager_backend.dto.SignupRequest;
import com.fieldsmanager.fields_manager_backend.entity.User;
import com.fieldsmanager.fields_manager_backend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
}

