package com.cs322.ors.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cs322.ors.db.TransactionRepository;
import com.cs322.ors.db.UserRepository;
import com.cs322.ors.model.Transaction;
import com.cs322.ors.model.User;



@Service
public class TransactionService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	public Transaction createTransaction(Transaction transaction) throws Exception {
		User customer = transaction.getUserid();
		BigDecimal currVipSum = customer.getVipSum();
		if(transaction.getType() == 0) {
			BigDecimal sum = getTransactionSumByCustomer(customer);
			
			if(sum.doubleValue() < transaction.getAmount().doubleValue()) {
				throw new Exception("Cant perform transaction");
			}
		}
		currVipSum = currVipSum.add(transaction.getAmount());
		customer.setVipSum(currVipSum);
		userRepository.save(customer);
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
		transactionRepository.deleteById(id);
    }
	
	public BigDecimal getTransactionSumByCustomer(User customer) {
		List<Transaction>  transactions = getTransactionsbyCustomer(customer.getId());
		BigDecimal zero = BigDecimal.valueOf(0);
		BigDecimal sum = zero;
		if(!transactions.isEmpty()) {
			for (Transaction transaction : transactions) {
			    boolean isPositive = transaction.getType() == 1;
				if(isPositive) {
					sum = sum.add(transaction.getAmount());
				}else {
					sum = sum.subtract(transaction.getAmount());
				}
			}
		}
		return sum.compareTo(zero) == -1 ? zero : sum;
	}


}