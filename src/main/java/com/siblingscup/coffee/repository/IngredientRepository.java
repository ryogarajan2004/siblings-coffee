package com.siblingscup.coffee.repository;

import com.siblingscup.coffee.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query("SELECT i FROM Ingredient i WHERE i.stockQuantity <= i.lowStockThreshold")
    List<Ingredient> findByStockQuantityLessThanThreshold();
}
