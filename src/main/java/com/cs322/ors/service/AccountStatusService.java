package com.cs322.ors.service;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.model.User;

@Service
public class AccountStatusService {
	
	@Autowired
	UserWarningService userWarningService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	VipService vipService;
	
	@Autowired
	ChefStatusService chefStatusService;
	
	public void updateStatus(User user) {
		int warnings = userWarningService.count(user.getId());
		vipService.checkAndSetVIP(user);
		boolean isVIP = user.getRole() == "VIP";
		boolean isChef = user.getRole() == "CHEF";

		boolean isCustomer = user.getRole() == "CUSTOMER" || isVIP;
		if(isVIP && warnings == 2) {
			user.setVipSum(BigDecimal.ZERO);
			user.setRole("CUSTOMER");
			userWarningService.deleteAllByUser(user.getId());
			userService.updateUser(user);
			
		} 
		if(isCustomer && warnings >= 3) {			
			user.setClosed(true);
			userService.updateUser(user);
		} 		
		if(isChef) {
			chefStatusService.promoteOrDemoteChef(user);
			userService.updateUser(user);
		}
		
	}

}
