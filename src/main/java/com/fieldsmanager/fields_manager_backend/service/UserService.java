package com.fieldsmanager.fields_manager_backend.service;
import com.fieldsmanager.fields_manager_backend.dto.UpdateProfileRequest;
import com.fieldsmanager.fields_manager_backend.repository.UserRepository;
import com.fieldsmanager.fields_manager_backend.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fieldsmanager.fields_manager_backend.dto.ResetPasswordRequest;

@Service
public class UserService {

    private final UserRepository userRepository;



    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User registerUser(String name, String email, String phone, String password) {
        User user = new User(name, email, phone, password, "ADMIN", "ACTIVE");
        return userRepository.save(user);
    }

    public String requestReset(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("No user with this email"));

        String code = String.format("%06d", new java.security.SecureRandom().nextInt(1_000_000));
        user.setResetCode(code);
        user.setResetCodeExpiry(java.time.LocalDateTime.now().plusMinutes(15));
        userRepository.save(user);
        return code;
    }

    public void confirmReset(String email, String code, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("No user with this email"));

        if (user.getResetCode() == null || user.getResetCodeExpiry() == null) {
            throw new IllegalArgumentException("No reset code requested");
        }
        if (java.time.LocalDateTime.now().isAfter(user.getResetCodeExpiry())) {
            throw new IllegalArgumentException("Reset code expired");
        }
        if (!user.getResetCode().equals(code)) {
            throw new IllegalArgumentException("Invalid reset code");
        }

        user.setPassword(newPassword);
        user.setResetCode(null);
        user.setResetCodeExpiry(null);
        userRepository.save(user);
    }

    public User updateProfile(String email, UpdateProfileRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        return userRepository.save(user);
    }

}

