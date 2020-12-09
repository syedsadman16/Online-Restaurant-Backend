package com.cs322.ors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.ChefJobRepository;
import com.cs322.ors.model.ChefJob;
import com.cs322.ors.model.DeliveryJobs;
import com.cs322.ors.model.Order;


@Service
public class ChefJobService {
	
	@Autowired
	ChefJobRepository chefJobRepository;
	
	@Autowired
	OrderService orderService;

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
			ChefJob job  = chefJobDB.get();
			job.setCompleted(!chefJobDB.get().isCompleted());   //updates to opposite of what is the current completion status
			Order order = job.getOrder();
			DeliveryJobs deliveryJob = order.getDeliveryJob();
	        boolean deliveryJobCompleted = deliveryJob.getStatus() == 2;
	        if(order.getType() == 1 && deliveryJobCompleted) {
				order.setCompleted(true);
				orderService.updateOrder(order, order.getId());
			}	
			if(order.getType() == 0 || order.getType() == 2) {
				order.setCompleted(true);
				orderService.updateOrder(order, order.getId());
			}			
			chefJobRepository.save(job);
		}
		
	}

	
	public void deleteJob(long id) {
		chefJobRepository.deleteById(id);
	}
	
	
	
	
}
