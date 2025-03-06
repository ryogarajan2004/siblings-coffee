package com.siblingscup.coffee.controller;


import com.siblingscup.coffee.model.Transaction;
import com.siblingscup.coffee.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @GetMapping
    public List<Transaction>getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction>getTransactionById(@PathVariable Long id){
        Optional <Transaction> transaction=transactionService.getTransactionById(id);

        return transaction.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }


    @PostMapping("/create")
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return transactionService.saveTransaction(transaction);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteIngredient(@PathVariable Long id){
        transactionService.deleteTransaction(
                id
        );

        return ResponseEntity.ok().build();
    }
}
