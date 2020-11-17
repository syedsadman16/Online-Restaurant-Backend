package com.cs322.ors.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cs322.ors.model.Order;
import com.cs322.ors.model.Transaction;
import com.cs322.ors.model.User;

public class VipService {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	TransactionService transactionService;
	
	public boolean checkVIP(User customer) {
		List<Order> orderList = orderService.getOrderByUser(customer.getId());
		List<Transaction> transactionList = transactionService.getTransactionsbyCustomer(customer.getId());
		boolean isFiftyOrders = orderList.size() >= 50;
		boolean spentFiveHundred = false;
		
		double transactionSum = 0;
		for (int i = 0; i < transactionList.size() || transactionSum >=500 ; i++) {
			transactionSum += transactionList.get(i).getAmount().doubleValue();
		}
		
		if (transactionSum >= 500) {
			spentFiveHundred = true;
		}
		
		return isFiftyOrders || spentFiveHundred;
	}
	
	public void checkAndSetVIP(User Customer) {
		if (checkVIP(Customer) == true) {
			Customer.setRole("VIP");
		}
	}
	
	public void applyDiscount(User Customer) {
		if(Customer.getRole() == "VIP") {		//apply 10% discount to order.
			
		}
	}
}
