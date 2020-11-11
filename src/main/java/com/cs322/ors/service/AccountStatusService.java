package com.cs322.ors.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.model.User;

@Service
public class AccountStatusService {
	
	//@Autowired
	//WarningService warningService;
	
	@Autowired
	UserService userService;
	
	public void updateStatus(User user) {
		//int warnings = warningService.count(user.getId())
		int warnings = 0;
		boolean isVIP = user.getRole() == "VIP";
		boolean isCustomer = user.getRole() == "CUSTOMER" || isVIP;
		if(isVIP && warnings == 2) {
			user.setRole("CUSTOMER");
			//warningService.deleteAll(user.getId())
			warnings = 0;
			userService.updateUser(user);
			
		} 
		if(isCustomer && warnings >= 3) {			
			user.setClosed(true);
			userService.updateUser(user);
		} 		
	}

}
