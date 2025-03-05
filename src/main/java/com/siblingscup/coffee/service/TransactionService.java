

package com.siblingscup.coffee.service;

import com.siblingscup.coffee.model.Transaction;
import com.siblingscup.coffee.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> updateTransaction(Long id, Transaction updatedTransaction) {
        return transactionRepository.findById(id).map(existingTransaction -> {
            if (updatedTransaction.getSale() != null) {
                existingTransaction.setSale(updatedTransaction.getSale());
            }
            if (updatedTransaction.getAmount() != 0) {
                existingTransaction.setAmount(updatedTransaction.getAmount());
            }
            if (updatedTransaction.getPaymentType() != null) {
                existingTransaction.setPaymentType(updatedTransaction.getPaymentType());
            }
            return transactionRepository.save(existingTransaction);
        });
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
