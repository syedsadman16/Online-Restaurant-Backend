package com.cs322.ors.controller;

import com.cs322.ors.model.ChefJob;
import com.cs322.ors.model.DeliveryJobs;
import com.cs322.ors.model.User;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.ChefJobService;
import com.cs322.ors.service.DeliveryJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobsController {

    @Autowired
    DeliveryJobsService deliveryJobsService;
    
    @Autowired
    ChefJobService chefJobService;

    @GetMapping("/delivery")
    @PreAuthorize("hasAnyRole('MANAGER','CHEF','DELIVERER')")
    public List<DeliveryJobs> getAllDeliveryJobs(){
        return deliveryJobsService.getAllDeliveryJobs();
    }

    @GetMapping("/delivery/{status}")
    @PreAuthorize("hasAnyRole('MANAGER','DELIVERER')")
    public List<DeliveryJobs> getDeliveryJobsByStatus(int status){
        return deliveryJobsService.getDeliveryByStatus(status);
    }

    @PostMapping("/delivery/addDeliveryJob")
    @PreAuthorize("hasAnyRole('MANAGER','CHEF')")
    public void addNewDeliveryJob(@RequestBody DeliveryJobs jobs){
        deliveryJobsService.addDeliveryJob(jobs);
    }

    @PostMapping("/delivery/acceptJob/{jobId}")
    @PreAuthorize("isAuthenticated()")
    public void acceptDeliveryJob(@PathVariable Long jobId, Authentication authUser){
        User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
        if(currentUser.getRole() == "DELIVERER") {
            deliveryJobsService.acceptDeliveryJob(jobId, currentUser);
        }
    }

    @PostMapping("/delivery/cancelJob/{jobId}")
    @PreAuthorize("hasAnyRole('MANAGER','CHEF','DELIVERER')")
    public void cancelDeliveryJob(@PathVariable Long jobId){
        deliveryJobsService.cancelDeliveryJob(jobId);
    }


    @PostMapping("/delivery/updateJob")
    @PreAuthorize("hasAnyRole('MANAGER','CHEF')")
    public void updateDeliveryJob(@RequestBody DeliveryJobs updatedJob){
        deliveryJobsService.updateDeliveryJob(updatedJob);
    }

    @DeleteMapping("/delivery/removeJob/{jobId}")
    @PreAuthorize("hasAnyRole('MANAGER','CHEF')")
    public void removeDeliveryJob(@PathVariable Long jobId){
        deliveryJobsService.deleteDeliveryJob(jobId);
    }
    
//     ------------------------------------------------------  ChefJob Controller ---------------------------------//
    
    
    @GetMapping("/chefJob")
    @PreAuthorize("hasRole('MANAGER')")
    public List<ChefJob> getAllChefJobs(){
        return chefJobService.getAllchefjobs();
    }
    
    
    @GetMapping("/chefJob/{status}")    //get by if status is completed or not
    @PreAuthorize("hasAnyRole('MANAGER','CHEF')")
    public List<ChefJob> getChefJobsByStatus(@PathVariable("status") boolean status, Authentication authUser){
    	User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
    	
		if (currentUser.getRole() == "MANAGER") {   
	        return chefJobService.getChefJobByStatus(status);
		} else {
			return chefJobService.getChefJobByUserAndStatus(currentUser.getId(),status); 
		}
    }
    
    @PutMapping("/chefJob/{id}")
    @PreAuthorize("hasAnyRole('MANAGER','CHEF')")
    public void updateStatus(@PathVariable long id) {
    	chefJobService.updateCompletionStatus(id);
    }
    
   //in case delete is needed 
   @DeleteMapping("/chefJob/{id}")
   @PreAuthorize("hasAnyRole('MANAGER','CHEF')")
   public void deleteJob(@PathVariable long id) {
	   chefJobService.deleteJob(id);
   }
   
    

}
