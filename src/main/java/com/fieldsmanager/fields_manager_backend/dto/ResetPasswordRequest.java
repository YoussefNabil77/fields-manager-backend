package com.fieldsmanager.fields_manager_backend.dto;
import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
}