package com.cs322.ors.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.UserRepository;
import com.cs322.ors.model.User;


@Service
public class DbInit implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User admin = new User("manager", "manager", "MANAGER", "");
		User customer = new User("customer", "customer", "CUSTOMER", "");
		
		List<User> users = Arrays.asList(admin, customer);
		userRepository.saveAll(users);
		
	}
}
