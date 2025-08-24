package com.fieldsmanager.fields_manager_backend.entity;

//
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "user")
@Data
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    @Column(nullable = false)
    private String password;

    private String role;
    private String status;
    private String resetCode;
    private LocalDateTime resetCodeExpiry;

    public User(String name, String email, String phone, String password, String role, String status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.status = status;
    }



    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() { return role; }
}