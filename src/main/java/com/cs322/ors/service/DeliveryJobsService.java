package com.cs322.ors.service;

import com.cs322.ors.db.DeliveryJobsRepository;

import com.cs322.ors.model.DeliveryJobs;
import com.cs322.ors.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeliveryJobsService {
    
    @Autowired
    DeliveryJobsRepository deliveryJobsRepository;

    /*
     * Return all jobs that are posted
     */
    public List<DeliveryJobs> getAllDeliveryJobs(){
        return deliveryJobsRepository.findAll();
    }

    /*
     * Retrieve jobs that based on status code
     * 1 - In progress
     * 0 - Available
     */
    public List<DeliveryJobs> getDeliveryByStatus(int status){
        return deliveryJobsRepository.findByStatus(status);
    }

    /*
     * Add job to table
     */
    public void addDeliveryJob(DeliveryJobs jobs){
        deliveryJobsRepository.save(jobs);
    }

    /*
     * When deliverer accepts job, set them as deliverer in the Order and
     * add to List<DeliveryJobs> in their user class. Change status to 1
     */
    public void acceptDeliveryJob(Long jobId, User user){
        DeliveryJobs jobs = deliveryJobsRepository.findById(jobId).get();
        jobs.getOrder().setDeliveryPerson(user);
        user.getDeliveryJobs().add(jobs);
        jobs.setStatus(1);
    }

    /*
     * Update DeliveryJobs in table and User List
     */
    public void updateDeliveryJob(DeliveryJobs jobs){
        User delivery = jobs.getOrder().getDeliveryPerson();
        delivery.replaceDeliveryJob(jobs);
        deliveryJobsRepository.save(jobs);
    }

    /*
     * Delete job from table
     */
    public void deleteDeliveryJob(Long id){
        deliveryJobsRepository.deleteById(id);
    }

    /*
     * Remove as deliverer in the Order and remove reference from
     * List<DeliveryJobs> in user class. Change status code back to 0
     */
    public void cancelDeliveryJob(Long jobId){
        DeliveryJobs jobs = deliveryJobsRepository.findById(jobId).get();
        User delivery = jobs.getOrder().getDeliveryPerson();
        jobs.getOrder().setDeliveryPerson(new User());
        delivery.getDeliveryJobs().remove(jobs);
        jobs.setStatus(0);
    }

    public void deliveryJobCompleted(){

    }

}
