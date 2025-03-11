package com.siblingscup.coffee.controller;

import com.siblingscup.coffee.service.SupplierOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplier-orders")
@RequiredArgsConstructor
public class SupplierOrderController {
    private final SupplierOrderService supplierOrderService;

    @PostMapping("/auto-order")
    public ResponseEntity<String> triggerAutoOrder() {
        supplierOrderService.autoOrderLowStockItems();
        return ResponseEntity.ok("Auto-ordering triggered for low-stock ingredients.");
    }
}
