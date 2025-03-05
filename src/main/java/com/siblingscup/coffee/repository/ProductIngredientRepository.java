package com.siblingscup.coffee.repository;

import com.siblingscup.coffee.model.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductIngredientRepository extends JpaRepository<ProductIngredient,Long> {
}
