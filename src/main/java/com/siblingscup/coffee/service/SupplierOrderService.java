package com.siblingscup.coffee.service;

import com.siblingscup.coffee.model.Ingredient;
import com.siblingscup.coffee.model.Supplier;
import com.siblingscup.coffee.model.SupplierOrder;
import com.siblingscup.coffee.model.SupplierOrderItem;
import com.siblingscup.coffee.repository.IngredientRepository;
import com.siblingscup.coffee.repository.SupplierOrderItemRepository;
import com.siblingscup.coffee.repository.SupplierOrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierOrderService {
    private final SupplierOrderRepository supplierOrderRepository;
    private final SupplierOrderItemRepository supplierOrderItemRepository;
    private final IngredientRepository ingredientRepository;

    @Transactional
    public void autoOrderLowStockItems() {
        List<Ingredient> lowStockIngredients = ingredientRepository.findByStockQuantityLessThanThreshold();

        Map<Supplier, List<Ingredient>> groupedBySupplier = lowStockIngredients.stream()
                .collect(Collectors.groupingBy(Ingredient::getSupplier));

        for (Map.Entry<Supplier, List<Ingredient>> entry : groupedBySupplier.entrySet()) {
            SupplierOrder order = new SupplierOrder();
            order.setSupplier(entry.getKey());
            order.setOrderDate(LocalDateTime.now());
            order.setStatus("PENDING");
            supplierOrderRepository.save(order);

            List<SupplierOrderItem> orderItems = new ArrayList<>();
            for (Ingredient ingredient : entry.getValue()) {
                SupplierOrderItem item = new SupplierOrderItem();
                item.setSupplierOrder(order);
                item.setIngredient(ingredient);
                item.setQuantity(ingredient.getLowStockThreshold() * 2); // Order double the threshold
                item.setPrice(ingredient.getPrice());
                orderItems.add(item);
            }
            supplierOrderItemRepository.saveAll(orderItems);
        }
    }
}
