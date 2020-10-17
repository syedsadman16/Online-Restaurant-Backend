package com.cs322.ors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.userRepository;
import com.cs322.ors.model.*;

@Service
public class userService {

	@Autowired
	private  userRepository userRepository ;

	

}
