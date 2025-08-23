package com.fieldsmanager.fields_manager_backend.service;
import com.fieldsmanager.fields_manager_backend.repository.UserRepository;
import com.fieldsmanager.fields_manager_backend.entity.User;
import org.springframework.stereotype.Service;
//

@Service
public class UserService {

    // Spring will inject the repository automatically
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private  UserRepository userRepository;


    public User registerUser(String name, String email, String phone, String password) {
        User user = new User(name, email, phone, password, "PLAYER", "ACTIVE");
        return userRepository.save(user);
    }
}
