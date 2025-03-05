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



    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ProductIngredient>ingredients;

    private double profitMargin;

    @Transient
    private double price;

    public double calculatePrice(){
        double totalCost=0.0;
        for (ProductIngredient pi:ingredients){
            totalCost+=pi.getIngredient().getPrice()*pi.getQuantityRequired();
        }
        return totalCost+profitMargin;
    }
}
