package com.siblingscup.coffee.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime orderTime;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem>items;

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}

 enum OrderStatus {
    NEW,
    PREPARING,
    COMPLETED,
    CANCELLED;
}
