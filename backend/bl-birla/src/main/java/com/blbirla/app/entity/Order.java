package com.blbirla.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(optional=false)
    private User user;

    @Column
    private String status = "PENDING";

    @Column
    private Double totalAmount;

    @Column
    private String city;

    @Column
    private Instant orderDate = Instant.now();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    // convenience
    public void addItem(OrderItem oi) {
        items.add(oi);
        oi.setOrder(this);
    }
    // getters/setters
}

