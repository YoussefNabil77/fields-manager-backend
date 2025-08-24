package com.fieldsmanager.fields_manager_backend.dto;
import lombok.Data;

@Data
public class ResetPasswordConfirmRequest {
    private String email;
    private String code;
    private String newPassword;
}
