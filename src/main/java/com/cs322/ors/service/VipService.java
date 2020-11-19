package com.cs322.ors.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.model.Order;
import com.cs322.ors.model.Transaction;
import com.cs322.ors.model.User;

@Service
public class VipService {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;
	
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
			userService.updateUser(Customer);			
		}
	}
	
	public BigDecimal applyDiscount(BigDecimal amount, User customer) {
		BigDecimal discount = amount.multiply(BigDecimal.valueOf(0.1));
		if(customer.getRole() == "VIP") {		//apply 10% discount to order.
			amount = amount.subtract(discount);
		}
		return amount;
	}
}
