package com.siblingscup.coffee.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
@Getter
@Setter
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private double totalAmount;
    private LocalDateTime saleTime;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

}
