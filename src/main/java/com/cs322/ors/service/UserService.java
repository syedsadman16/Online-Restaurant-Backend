package com.cs322.ors.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import org.springframework.stereotype.Service;


import com.cs322.ors.db.UserRepository;
import com.cs322.ors.model.Dish;
import com.cs322.ors.model.Salary;
import com.cs322.ors.model.Transaction;
import com.cs322.ors.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User newUser) {	
		boolean isVIP = newUser.getRole() == "VIP";
		boolean isManager = newUser.getRole() == "MANAGER";
		boolean isCustomer = newUser.getRole() == "CUSTOMER" || isVIP;	
		
		if(isCustomer) {
			Transaction bonus = new Transaction(newUser, BigDecimal.valueOf(300), "Sign up bonus", 1);
			newUser.setTransactions(Arrays.asList(bonus));			
		}
		if(!isCustomer && !isManager) {
			Salary initialSalary = new Salary(newUser, BigDecimal.valueOf(30000));
			newUser.setSalary(initialSalary);
		}
		return userRepository.save(newUser);
	}

	public List<User> findUserByRole(String role){
		return userRepository.findByRole(role);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUserById(long id) {
		return userRepository.findById(id);

	}

	public User patchUser(long id, Map<Object,Object> fields) {
		User  user = userRepository.findById(id).get();
		if (user != null) {
			 // Map key is field name, v is value
		    fields.forEach((k, v) -> {
		       // use reflection to get field k on model and set it to value v
		        Field field = ReflectionUtils.findField(User.class, (String) k);
		        field.setAccessible(true);
		        ReflectionUtils.setField(field, user, v);
		    });
		    return userRepository.save(user);
		}
		return user;
		
	}
	
	public void updateUser(User user) {
		Optional<User> UserDB = this.userRepository.findById(user.getId());
		
		if(UserDB.isPresent()) {			
			userRepository.save(user);
		}
	}


	public void deleteUser(long id) {
		Optional<User> userDb = this.userRepository.findById(id);

		if (userDb.isPresent()) {
			userRepository.delete(userDb.get());
		}

	}

}
