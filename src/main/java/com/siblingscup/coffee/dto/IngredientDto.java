package com.siblingscup.coffee.dto;

import com.siblingscup.coffee.model.Unit;
import lombok.Data;

@Data
public class IngredientDto {

    private Long id;
    private String name;
    private int stockQuantity;
    private Unit baseUnit;
    private double conversionFactor;
    private int lowStockThreshold;
    private double price;
    private Long supplierId;
}
