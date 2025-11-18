package com.blbirla.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Order order;

    @ManyToOne(optional=false)
    private Medicine medicine;

    @Column
    private Integer quantity;

    @Column
    private Double unitPrice;

    @Column
    private Double amount;
    // getters/setters
}

