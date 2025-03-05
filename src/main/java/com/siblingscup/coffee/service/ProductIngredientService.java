package com.siblingscup.coffee.service;


import com.siblingscup.coffee.model.ProductIngredient;
import com.siblingscup.coffee.model.ProductIngredient;
import com.siblingscup.coffee.repository.ProductIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductIngredientService {

    private ProductIngredientRepository repository;

    public List<ProductIngredient>getAllProductIngredients(){
        return repository.findAll();
    }

    public Optional<ProductIngredient>getProductIngredientById(Long id){
        return repository.findById(id);
    }

    public ProductIngredient saveProductIngredient(ProductIngredient productIngredient){
        return repository.save(productIngredient);

    }
    public void deleteProductIngredient(Long id){
        repository.deleteById(id);
    }
}
