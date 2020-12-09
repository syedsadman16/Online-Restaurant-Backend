package com.cs322.ors.service;

import com.cs322.ors.db.DeliveryJobsRepository;
import com.cs322.ors.model.ChefJob;
import com.cs322.ors.model.DeliveryJobs;
import com.cs322.ors.model.DeliveryStatus;
import com.cs322.ors.model.Order;
import com.cs322.ors.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeliveryJobsService {
    
    @Autowired
    DeliveryJobsRepository deliveryJobsRepository;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

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
     * When deliverer accepts job, set them as delivery person in the Order table
     * In the User class, add List<DeliveryJobs> to deliverers table
     * Change DeliveryJob status to 1 to indicate job in progress
     */
    public void acceptDeliveryJob(Long jobId, User user){
        DeliveryJobs jobs = deliveryJobsRepository.findById(jobId).get();
        Order updatedOrder = jobs.getOrder();
        updatedOrder.setDeliveryPerson(user);
        user.getDeliveryJobs().add(jobs);
        jobs.setStatus(1);

        // Persist to corresponding tables
        orderService.updateOrder(updatedOrder, jobs.getOrder().getId());
        userService.updateUser(user);
        deliveryJobsRepository.save(jobs);
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
     * Check if user was assigned to a job - if yes, update users jobs list
     * by deleting job
     * Next, update Order by changing delivery person back to default null
     */
    public void deleteDeliveryJob(Long id){
        DeliveryJobs deleteJob = deliveryJobsRepository.findById(id).get();
        Order deleteOrder = deleteJob.getOrder();
        User deleteUser =  deleteJob.getOrder().getDeliveryPerson();
        if(deleteUser != null){
            deleteUser.getDeliveryJobs().remove(deleteJob);
            userService.updateUser(deleteUser);
        }

        deleteOrder.setDeliveryPerson(null);
        orderService.updateOrder(deleteOrder, deleteJob.getOrder().getId());

        deliveryJobsRepository.delete(deleteJob);
    }

    /*
     * Remove as deliverer in the Order and remove reference from
     * List<DeliveryJobs> in user class. Change status code back to 0
     */
    public void cancelDeliveryJob(Long jobId){
        DeliveryJobs cancelledJobs = deliveryJobsRepository.findById(jobId).get();
        cancelledJobs.setStatus(0);
        User delivery = cancelledJobs.getOrder().getDeliveryPerson();
        delivery.getDeliveryJobs().remove(cancelledJobs);
        Order cancelledOrder = cancelledJobs.getOrder();
        cancelledOrder.setDeliveryPerson(null);

        userService.updateUser(delivery);
        orderService.updateOrder(cancelledOrder, cancelledJobs.getOrder().getId());
        deliveryJobsRepository.save(cancelledJobs);
    }


    /*
     * When delivery is complete, update the delivery and order status
     */
    public void deliveryJobCompleted(Long id){
        DeliveryJobs completedJob = deliveryJobsRepository.findById(id).get();
       // DeliveryStatus updateDeliveryStatus = completedJobs.getOrder().getDeliveryStatus();
        //updateDeliveryStatus.setDelivered(true);
        //Order completedOrder = completedJobs.getOrder();
        //completedOrder.setDeliveryStatus(updateDeliveryStatus);
        Order order = completedJob.getOrder();
        ChefJob chefJob = order.getChefJob();
        boolean chefJobCompleted = chefJob.isCompleted();
        if(order.getType() == 1 && chefJobCompleted) {
			order.setCompleted(true);
			orderService.updateOrder(order, order.getId());
		}		
        completedJob.setStatus(2);

        // Enter amt to be paid here

        //Save to tables
        //orderService.updateOrder(completedOrder, completedJobs.getOrder().getId());
        deliveryJobsRepository.save(completedJob);
    }

}
