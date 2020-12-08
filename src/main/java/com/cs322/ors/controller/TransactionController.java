package com.cs322.ors.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cs322.ors.model.Transaction;
import com.cs322.ors.model.User;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@GetMapping
	@PreAuthorize("hasAnyRole('MANAGER','VIP','CUSTOMER')")
	public List<Transaction> listTransaction(Authentication authUser) {
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		if (currentUser.getRole() == "MANAGER") {   //if manager give all salaries
			return transactionService.getAllTransactions();
		}
		else {
			return transactionService.getTransactionsbyCustomer(currentUser.getId());
		}
	
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('MANAGER','VIP','CUSTOMER')")
	public Transaction createTransaction(@Valid @RequestBody Transaction newTransaction) {
		try {
			return transactionService.createTransaction(newTransaction);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('MANAGER')") 
	public void deleteTransaction(@PathVariable long id) {
		transactionService.deleteTransaction(id);
	}
}




