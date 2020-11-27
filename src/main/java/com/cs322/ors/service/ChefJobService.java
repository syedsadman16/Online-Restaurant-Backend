package com.cs322.ors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.ChefJobRepository;
import com.cs322.ors.model.ChefJob;


@Service
public class ChefJobService {
	
	@Autowired
	ChefJobRepository chefJobRepository;

	public List<ChefJob> getAllchefjobs() {
		return chefJobRepository.findAll();
	}

	
	public List<ChefJob> getChefJobByStatus(boolean status) {
		return chefJobRepository.findByCompleted(status);
	}
	

	public List<ChefJob> getChefJobByUserAndStatus(long id, boolean status) {
		return chefJobRepository.findByUser_idAndStatus(id,status);
	}
	
	
	public void addChefJob(ChefJob chefjob){
	     chefJobRepository.save(chefjob);
	 }
	

	public void updateCompletionStatus(long id) {
		Optional<ChefJob> chefJobDB = chefJobRepository.findById(id);
		
		if (chefJobDB.isPresent()) {
			chefJobDB.get().setCompleted(!chefJobDB.get().isCompleted());   //updates to opposite of what is the current completion status
			chefJobRepository.save(chefJobDB.get());
		}
		
	}

	
	public void deleteJob(long id) {
		chefJobRepository.deleteById(id);
	}
	
	
	
	
}
