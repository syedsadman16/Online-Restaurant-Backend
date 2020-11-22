package com.cs322.ors.service;


import com.cs322.ors.db.ClaimsRepository;
import com.cs322.ors.model.Claims;
import com.cs322.ors.model.User;
import com.cs322.ors.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimsService {

    @Autowired
    private ClaimsRepository claimsRepository;
    @Autowired
    private UserService userService;

    /*
     * Any manager has access to all claims
     */
    public List<Claims> getAllClaims(){
        return claimsRepository.findAll();
    }

    /*
     * If manager decides to dismiss a claim, remove it from the table
     * and recalculate rating for the user
     */
    public void dismissClaim(User user, Long claimId){
        UserRating dismissedRating = claimsRepository.findById(claimId).get().getUserRating();
        user.getRatingList().remove(dismissedRating);
        user.setRating(user.calculateAverageRating());
        userService.updateUser(user);
        deleteClaim(claimId);
    }

//    /*
//     * User can view their claims
//     */
//    public List<Claims> getUsersClaims(User user){
//        return claimsRepository.findByUser
//    }
    

    /*
     * Users can submit a claim for a particular rating
     */
    public void postClaim(Claims claims){
        claimsRepository.save(claims);
    }

    /*
     * Update
     */
    public void updateClaim(Claims claims){
        claimsRepository.save(claims);
    }

     /*
      * Delete
      */
    public void deleteClaim(Long id){
        Claims claim = claimsRepository.findById(id).get();
        claimsRepository.delete(claim);
    }
}
