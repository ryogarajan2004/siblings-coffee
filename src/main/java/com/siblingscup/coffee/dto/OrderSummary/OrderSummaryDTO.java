package com.siblingscup.coffee.dto.OrderSummary;

import java.time.LocalDateTime;
import java.util.List;

import com.siblingscup.coffee.model.OrderStatus;

import lombok.Data;

@Data
public class OrderSummaryDTO {
    private Long id;
    private LocalDateTime orderTime;
    private double totalAmount;
    private OrderStatus status;
    private List<OrderItemSummaryDTO> items;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderTime() {
        return this.orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItemSummaryDTO> getItems() {
        return this.items;
    }

    public void setItems(List<OrderItemSummaryDTO> items) {
        this.items = items;
    }

}
