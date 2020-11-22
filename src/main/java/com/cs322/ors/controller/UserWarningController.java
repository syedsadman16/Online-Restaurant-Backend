package com.cs322.ors.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.cs322.ors.model.User;
import com.cs322.ors.model.UserWarning;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.UserWarningService;

@RestController
@RequestMapping("/api/Warning")
public class UserWarningController {

	
	@Autowired
	public UserWarningService userWarningService;
	
									//users can only see their own warnings
	@GetMapping  					//manager sees all warnings
	@PreAuthorize("isAuthenticated()")
	public List<UserWarning> getOrders(Authentication authUser){
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		if(currentUser.getRole() == "MANAGER") {
			return userWarningService.getAllWarnings();
		}else {
			return userWarningService.getWarningByUser(currentUser.getId());
		}
		
	}

	// Manager can add a new warning
	@PostMapping
	@PreAuthorize("hasRole('MANAGER')")
	public void createWarning(UserWarning warning){
		userWarningService.createWarning(warning);
	}
	
	
	@GetMapping("/{warningId}")	//Get warnings by id	
	@PreAuthorize("isAuthenticated()") 			
	public UserWarning getWarningWithId(@PathVariable long warningId, Authentication authUser, HttpServletResponse response){		
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		Optional<UserWarning> warning = userWarningService.getWarningById(warningId);
		if(warning.isPresent()) {
			UserWarning theWarning= warning.get();
			boolean isTheirs = theWarning.getUser().getId() == currentUser.getId();
			boolean isManager = currentUser.getRole() == "MANAGER"; 
			if(isTheirs || isManager) {
				return theWarning;
			}
			else {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				return null;
			}
		}
		else {
			return null;
			}		
	}

	
}

