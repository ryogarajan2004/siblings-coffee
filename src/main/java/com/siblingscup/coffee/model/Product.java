package com.siblingscup.coffee.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;
    private double profitMargin;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductIngredient> ingredients;

    @Column(nullable = true)
    private double price;

    public double calculatePrice() {
        double totalCost = 0.0;
        for (ProductIngredient pi : ingredients) {
            totalCost += pi.getIngredient().getPrice() * pi.getQuantityRequired();
        }
        return totalCost + profitMargin;
    }
}
