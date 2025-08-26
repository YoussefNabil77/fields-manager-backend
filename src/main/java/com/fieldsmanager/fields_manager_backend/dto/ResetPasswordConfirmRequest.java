package com.fieldsmanager.fields_manager_backend.dto;

public class ResetPasswordConfirmRequest {
    private String email;
    private String code;
    private String newPassword;


    public ResetPasswordConfirmRequest() {
    }

    public ResetPasswordConfirmRequest(String email, String code, String newPassword) {
        this.email = email;
        this.code = code;
        this.newPassword = newPassword;
    }


    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }

    public String getNewPassword() {
        return newPassword;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


}
