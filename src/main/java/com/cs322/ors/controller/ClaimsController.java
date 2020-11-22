package com.cs322.ors.controller;

import com.cs322.ors.model.Claims;
import com.cs322.ors.model.User;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.ClaimsService;
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

    @GetMapping
    @PreAuthorize("hasRole('MANAGER')")
    public List<Claims> getAllClaims(){
        return claimsService.getAllClaims();
    }

    @PostMapping("/dismissClaim/{claimId}")
    @PreAuthorize("hasRole('MANAGER')")
    public void dismissCLaim(@PathVariable Long claimId, Authentication authUser){
        User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
        claimsService.dismissClaim(currentUser, claimId);
    }

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

    @DeleteMapping("/deleteClaim/{id}")
    @PreAuthorize("isAuthenticated()")
    public void updateClaim(@PathVariable Long id){
        claimsService.deleteClaim(id);
    }

}
