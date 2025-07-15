package com.siblingscup.coffee.controller;

import com.siblingscup.coffee.dto.OrderRequestDTO;
import com.siblingscup.coffee.dto.UpdateStatusDTO;
import com.siblingscup.coffee.model.Order;
import com.siblingscup.coffee.model.OrderStatus;
import com.siblingscup.coffee.repository.OrderRepository;
import com.siblingscup.coffee.service.OrderBroadcastService;
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
    private OrderBroadcastService orderBroadcastService;

    @Autowired
    private OrderRepository orderRepo;
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

    @GetMapping("/active")
    public ResponseEntity<List<Order>> getActiveOrders() {
        List<Order> activeOrders = orderRepo.findByStatusNot(OrderStatus.SERVED);
        return ResponseEntity.ok(activeOrders);
    }


    @GetMapping("/kitchen")
    public ResponseEntity<List<Order>>getKitchenOrders(){
        List<Order>kitchenOrders=orderRepo.findByStatusIn(List.of(OrderStatus.NEW,OrderStatus.PREPARING));
        return ResponseEntity.ok(kitchenOrders);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody UpdateStatusDTO status) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        try {
            OrderStatus newStatus = OrderStatus.valueOf(status.getStatus().toUpperCase());
            order.setStatus(newStatus);
            orderRepo.save(order);

            List<Order> kitchenOrders=orderRepo.findByStatusNot(OrderStatus.SERVED);

            orderBroadcastService.broadcastKitchenOrders(kitchenOrders);

            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid status value:" + status.getStatus());
        }
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
