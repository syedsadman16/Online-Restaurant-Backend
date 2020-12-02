package com.cs322.ors.service;


import com.cs322.ors.db.ClaimsRepository;
import com.cs322.ors.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimsService {

    @Autowired
    private ClaimsRepository claimsRepository;
    @Autowired
    private UserRatingsService userRatingsService;
    @Autowired
    private UserWarningService userWarningService;

    /*
     * Any manager has access to all claims; not available to the user
     */
    public List<Claims> getAllClaims(){
        return claimsRepository.findAll();
    }


    /*
     * Return a claim based on ID
     */
    public Claims getClaimById(Long claimId){
        return claimsRepository.findById(claimId).get();
    }


    /*
     * User can view their own claims
     */
    public List<Claims> getUsersClaims(User user){
        return claimsRepository.findByVictim(user);
    }


    /*
     * Manager can deny a claim and set it as a warning
     */
    public void convertToWarning(Long claimId, String message) {
        User user = claimsRepository.findById(claimId).get().getVictim();
        if(message == ""){
            message = "Your request to dispute a review has been declined. This is a warning";
        }
        userWarningService.createWarning(new UserWarning(user, message));
        deleteClaim(claimId);
    }


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
        claimsRepository.findById(id).get().setUserRating(null);
        claimsRepository.delete(claimsRepository.findById(id).get());
    }
}
