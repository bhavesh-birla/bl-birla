package com.blbirla.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "medicines")
@Data
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String batchNo;

    @Column
    private String name;

    @Column
    private Double wholesalePrice;

    @Column
    private Integer stockQuantity;

    @Column
    private LocalDate expiryDate;
    // getters/setters
}

