package com.fieldsmanager.fields_manager_backend.dto;

public class ResetPasswordRequest {
    private String email;

    // Constructors
    public ResetPasswordRequest() {
    }

    public ResetPasswordRequest(String email) {
        this.email = email;
    }

    // Getter
    public String getEmail() {
        return email;
    }

    // Setter
    public void setEmail(String email) {
        this.email = email;
    }


}
