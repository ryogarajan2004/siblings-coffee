package com.siblingscup.coffee.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supplier_order_item")
@Getter
@Setter

public class SupplierOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_order_id",nullable = false)
    private SupplierOrder supplierOrder;


    @ManyToOne
    @JoinColumn(name = "ingredient_id",nullable = false)
    private Ingredient ingredient;

    private int quantity;
    private double price;
}
