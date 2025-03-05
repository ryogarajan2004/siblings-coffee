package com.siblingscup.coffee.service;

import com.siblingscup.coffee.model.Product;
import com.siblingscup.coffee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private ProductRepository repository;

    public List<Product>getAllProducts(){return repository.findAll();}

    public Optional<Product>getProductById(Long id){
        return repository.findById(id);
    }

    public Product saveProduct(Product product){
        return repository.save(product);
    }

    public void deleteProduct(Long id){
        repository.deleteById(id);
    }
}
