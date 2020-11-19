package com.cs322.ors.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs322.ors.model.Transaction;
import com.cs322.ors.service.TransactionService;
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@GetMapping
	@PreAuthorize("hasRole('MANAGER')")
	public List<Transaction> listTransaction() {
		return transactionService.getAllTransactions();
	}

	@PostMapping
	@PreAuthorize("hasRole('MANAGER')")
	public Transaction createTransaction(@Valid @RequestBody Transaction newTransaction) {
		return transactionService.createTransaction(newTransaction);
	}

	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('MANAGER')") 
	public void deleteTransaction(@PathVariable long id) {
		transactionService.deleteTransaction(id);
	}
}




