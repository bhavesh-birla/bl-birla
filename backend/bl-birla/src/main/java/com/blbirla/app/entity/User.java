package com.blbirla.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table()
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;

    @Column(nullable = false)
    private String shopName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column
    private String city;

    @Column
    private String role = "CUSTOMER";

    @Column
    private Instant createdAt = Instant.now();

//getter - setter
}

