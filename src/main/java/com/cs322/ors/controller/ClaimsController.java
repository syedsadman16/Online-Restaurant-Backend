package com.cs322.ors.controller;

import com.cs322.ors.model.Claims;
import com.cs322.ors.model.User;
import com.cs322.ors.model.UserRatings;
import com.cs322.ors.model.UserWarning;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.ClaimsService;
import com.cs322.ors.service.UserRatingsService;
import com.cs322.ors.service.UserWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimsController {

    @Autowired
    private ClaimsService claimsService;
    @Autowired
    private UserWarningService userWarningService;
    @Autowired
    private UserRatingsService userRatingsService;

    /*
     * Manager can view all the claims for any user
     * Registered users can view their pending claims
     */
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Claims> getAllClaims(Authentication authUser){
        User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
        if(currentUser.getRole() == "MANAGER" ){
            return claimsService.getAllClaims();
        } else {
            return claimsService.getUsersClaims(currentUser);
        }
    }

    /*
     * Accept the claim and remove the rating - this will also delete the claim
     */
    @PostMapping("/approveClaim/{claimId}")
    @PreAuthorize("hasRole('MANAGER')")
    public void denyClaimToWarning(@PathVariable Long claimId){
        userRatingsService.deleteUserRatings(claimsService.getClaimById(claimId).getUserRating().getId());
    }


    /*
     * Deny the users claim and set it as a warning
     */
    @PostMapping("/denyClaim/{claimId}")
    @PreAuthorize("hasRole('MANAGER')")
    public void denyClaimToWarning(@PathVariable Long claimId, @RequestBody UserWarning warning){
        claimsService.convertToWarning(claimId, warning.getMessage());
    }

    /*
     * Registered users can submit a claim for a rating by specifying
     * the UserRating and providing a messsage. The current user field in
     * the Claims model will be handled by the controller
     */
    @PostMapping("/submitClaim")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP','DELIVERER')")
    public void submitClaim(@RequestBody Claims claim, Authentication authUser){
        User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
        claim.setVictim(currentUser);
        claimsService.postClaim(claim);
    }

    @PostMapping("/updateClaim")
    @PreAuthorize("hasAnyRole('CUSTOMER','VIP','DELIVERER')")
    public void updateClaim(Claims updatedClaim){
        claimsService.updateClaim(updatedClaim);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public void deleteClaim(@PathVariable Long id){
        claimsService.deleteClaim(id);
    }

}
