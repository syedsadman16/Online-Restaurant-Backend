package com.cs322.ors.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.UserRepository;
import com.cs322.ors.model.User;

@Service
public class DbInit implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		// REAL DATA NEEDED TO INITIALIZE APPLICATION
		User user0 = new User("manager", "manager", "MANAGER"); 
		
		// DUMMY DATA
		User user1 = new User("c1", "c1", "CUSTOMER");
		User user3 = new User("c2", "c2", "VIP");
		User user4 = new User("e1", "e1", "CHEF");
		User user5 = new User("e2", "e2", "DELIVERER");
		List<User> users = Arrays.asList(user0,user1,user3,user4,user5);
		userRepository.saveAll(users);

	}
}
