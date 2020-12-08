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
	
	public List<UserWarning> getWarningByUser(long userId){
		return userWarningRepository.findByUser_Id(userId);
	}

	public int count(long userId) {        //counts warnings per user
		List<UserWarning> userWarnings = userWarningRepository.findByUser_Id(userId);
		return userWarnings.size();
	}

	public void deleteAllByUser(long userId) {		//deletes all warnings from a specific user using their user_id
		userWarningRepository.deleteByUser_Id(userId);
	}

	public void createWarning(UserWarning userWarning){
		userWarningRepository.save(userWarning);
	}
}
