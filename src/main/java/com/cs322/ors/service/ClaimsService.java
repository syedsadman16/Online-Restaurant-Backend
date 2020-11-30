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
    private UserService userService;
    @Autowired
    private UserWarningService userWarningService;

    /*
     * Any manager has access to all claims; not available to the user
     */
    public List<Claims> getAllClaims(){
        return claimsRepository.findAll();
    }


    /*
     * User can view their own claims
     */
    public List<Claims> getUsersClaims(User user){
        return claimsRepository.findByVictim(user);
    }


    /*
     * If manager decides to dismiss a claim, first find the user making the claim. Then, get
     * the UserRating from the claim and remove it from the users Rating list. Recalculate the
     * users average rating and  save tem to the userRepository. FInally, delete the claim from
     * the claims table
     */
    public void dismissClaim(Long claimId){
/*        User user = claimsRepository.findById(claimId).get().getVictim();*/
        UserRatings dismissedRating = claimsRepository.findById(claimId).get().getUserRating();
//        user.getRatingList().remove(dismissedRating);
//        user.setRating(user.calculateAverageRating());
//        userService.updateUser(user);
        deleteClaim(claimId);
    }


    /*
     * Manager can decline a claim and set it as a warning
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
        Optional<Claims> claim = this.claimsRepository.findById(id);
        claimsRepository.delete(claim.get());
    }
}
