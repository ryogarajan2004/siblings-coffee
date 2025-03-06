package com.siblingscup.coffee.controller;


import com.siblingscup.coffee.model.ProductIngredient;
import com.siblingscup.coffee.service.ProductIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prodIng")
public class ProductIngredientController {

    @Autowired
    private ProductIngredientService service;

    @GetMapping
    public List<ProductIngredient> getProductIngredients(){
        return  service.getAllProductIngredients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductIngredient> getIngredientById(@PathVariable Long id){
        Optional<ProductIngredient> pi=service.getProductIngredientById(id);
        return pi.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ProductIngredient createProductIngredient(@RequestBody ProductIngredient pi){
        return service.saveProductIngredient(pi);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProductIngredient(@PathVariable Long id){
        service.deleteProductIngredient(id);
        return ResponseEntity.ok().build();
    }


}
