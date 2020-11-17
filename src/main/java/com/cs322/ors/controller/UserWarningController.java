package com.cs322.ors.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs322.ors.model.User;
import com.cs322.ors.model.UserWarning;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.UserWarningService;

@RestController
@RequestMapping("/api/Warning")
public class UserWarningController {
	
	//  get /warning and /warning/warningid is below post put and delete should not be needed for now.
	
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

