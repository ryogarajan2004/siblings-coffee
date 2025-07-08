package com.siblingscup.coffee.dto;

import java.util.List;

import com.siblingscup.coffee.model.PaymentType;

import lombok.Data;

@Data
public class OrderDTO {
    private Long customerId;
    private List<OrderItemDTO> orderItems;
    private PaymentType paymentType;
}
