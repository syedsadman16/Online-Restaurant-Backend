package com.cs322.ors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.UserWarningRepository;
import com.cs322.ors.model.UserWarning;

@Service
public class UserWarningService {
	@Autowired 
	public UserWarningRepository userWarningRepository;

	public List<UserWarning> getAllWarnings() {
		return userWarningRepository.findAll();
	}

	public Optional<UserWarning> getWarningById(long warningId) {
		return userWarningRepository.findById(warningId);
	}

	public int count(long customerId) {        //counts warnings per customer
		List<UserWarning> customerWarnings = userWarningRepository.findByCustomer_Id(customerId);
		return customerWarnings.size();
	}

	public void deleteAllByCustomer(long customerId) {		//deletes all warnings from a specific customer using their customer_id
		userWarningRepository.deleteByCustomer_Id(customerId);
	}
}
