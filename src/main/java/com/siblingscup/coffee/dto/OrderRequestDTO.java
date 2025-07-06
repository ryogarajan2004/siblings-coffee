
package com.siblingscup.coffee.dto;

import java.util.List;

import lombok.Data;

/**
 *
 * @author yoga2405
 */
@Data
public class OrderRequestDTO {
    private Long customerId;
    private List<OrderItemDTO>items;
    private String paymentType;


    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemDTO> getItems() {
        return this.items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }


}
