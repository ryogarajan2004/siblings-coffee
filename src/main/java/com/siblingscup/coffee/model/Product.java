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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getProfitMargin() {
        return this.profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public List<ProductIngredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<ProductIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
