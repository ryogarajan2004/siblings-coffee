package com.siblingscup.coffee.controller;


import com.siblingscup.coffee.model.Supplier;
import com.siblingscup.coffee.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;


    @GetMapping
    public List<Supplier>getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id){
        Optional<Supplier> supplier= supplierService.getSupplierById(id);

        return supplier.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }


    @PostMapping("/create")
    public Supplier createSupplier(@RequestBody Supplier supplier){
        return supplierService.saveSupplier(supplier);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSupplier(@PathVariable Long id){
        supplierService.deleteSupplier(id);

        return ResponseEntity.ok().build();
    }
}
