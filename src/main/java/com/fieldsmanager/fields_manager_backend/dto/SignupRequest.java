package com.fieldsmanager.fields_manager_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
//import lombok.Data;
// lombok

//@Data
public class SignupRequest {
    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String phone;

    @Size(min = 6)
    private String password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}