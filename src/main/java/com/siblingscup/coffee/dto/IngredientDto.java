package com.siblingscup.coffee.dto;

import lombok.Data;

@Data
public class IngredientDto {
;
    private Long id;
    private String name;
    private int stockQuantity;
    private String baseUnit;
    private double conversionFactor;
    private int lowStockThreshold;
    private double price;
    private Long supplierId;
}
