package com.blbirla.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "LOGIN_REQUEST")
@Data
public class LoginRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;
}
