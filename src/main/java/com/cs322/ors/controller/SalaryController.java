package com.cs322.ors.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs322.ors.model.Salary;
import com.cs322.ors.model.User;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.SalaryService;

@RequestMapping("/api")
@RestController
public class SalaryController {
	
	@Autowired
	public SalaryService salaryService;
	
	@GetMapping("/salary")
	@PreAuthorize("hasAnyRole('CHEF','DELIVERER','MANAGER')")
	public List<Salary> getAllSalary(Authentication authUser){
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		if (currentUser.getRole() == "MANAGER") {   //if manager give all salaries
			return salaryService.getAllSalary();
		} else {
			return salaryService.getSalaryByUser(currentUser.getId());   // if regular employee show his own salary
		}
	}
	

	// There is no get method by salary id as it is a one to one relationship
	
	@PostMapping("/salary")
	@PreAuthorize("hasRole('MANAGER')")  //only managers can create salaries
	public void createSalary(@Valid @RequestBody Salary salary) {
		salaryService.createSalary(salary);
	}
	
	@PutMapping("/salary/{userId}")
	@PreAuthorize("hasRole('MANAGER')")  //only managers can update salaries
	public void updateSalary(BigDecimal amount,@PathVariable long userId) {
		salaryService.updateSalary(amount,userId);
	}
	
}
