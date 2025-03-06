package com.siblingscup.coffee.controller;


import com.siblingscup.coffee.model.Product;
import com.siblingscup.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product>getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product>product=productService.getProductById(id);

        return product.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        productService.deleteProduct( id);
        return ResponseEntity.ok().build();
    }
}

