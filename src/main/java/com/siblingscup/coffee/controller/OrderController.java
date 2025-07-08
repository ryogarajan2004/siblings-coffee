package com.siblingscup.coffee.controller;

import com.siblingscup.coffee.dto.OrderRequestDTO;
import com.siblingscup.coffee.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.siblingscup.coffee.dto.OrderSummary.OrderSummaryDTO;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderSummaryDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderSummaryDTO> getOrderById(@PathVariable Long id) {
        Optional<OrderSummaryDTO> order = orderService.getOrderById(id);

        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

   
    
    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDTO request) {
        try {
            OrderSummaryDTO order = orderService.createOrder(request);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to place order:" + e.getMessage());
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);

        return ResponseEntity.ok().build();
    }

}
