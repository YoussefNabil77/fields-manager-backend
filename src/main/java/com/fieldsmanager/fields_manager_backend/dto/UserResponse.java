package com.fieldsmanager.fields_manager_backend.dto;

//import lombok.AllArgsConstructor;
//import lombok.Data;

//@Data
//@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String name;
    private String email;
    private String role;
    private String status;

    public UserResponse(Integer id, String name, String email, String role, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
    }
}


