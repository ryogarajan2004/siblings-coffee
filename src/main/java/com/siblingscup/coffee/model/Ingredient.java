package com.siblingscup.coffee.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    private Unit unit;
    private double conversionFactor;
    private int lowStockThreshold;
    private double price;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
