package com.cs322.ors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cs322.ors.db.TransactionRepository;
import com.cs322.ors.model.Transaction;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public Transaction createTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}
	
	public List<Transaction> getTransactionsbyCustomer(long id) {
		return transactionRepository.findByUserid_id(id);
	}

	public Optional<Transaction> getTransactionById(long id) {
		return transactionRepository.findById(id);

    }
    
	public void deleteTransaction(long id) {
		Optional<Transaction> transactionDb = this.transactionRepository.findById(id);
		if (transactionDb.isPresent()) {
			transactionRepository.delete(transactionDb.get());
		}
    }
    


}