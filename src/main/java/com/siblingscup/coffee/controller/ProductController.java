package com.siblingscup.coffee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siblingscup.coffee.dto.ProductDto;
import com.siblingscup.coffee.model.Product;
import com.siblingscup.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(
            @RequestParam("image") MultipartFile file,
            @RequestParam("data") String productData) {

        try {
            // Convert JSON string to ProductDto
            ObjectMapper objectMapper = new ObjectMapper();
            ProductDto productDto = objectMapper.readValue(productData, ProductDto.class);

            // Handle file upload
            String imageUrl = productService.saveImage(file, productDto.getName());

            // Create and save product
            Product savedProduct = productService.createProduct(productDto, imageUrl);

            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
