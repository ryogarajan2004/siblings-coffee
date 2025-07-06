 package com.siblingscup.coffee.dto;

import lombok.Data;
import java.util.Objects;

@Data
public class OrderItemDTO {

    private Long productId;
    private int quantity;

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}