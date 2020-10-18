package com.cs322.ors.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs322.ors.model.Test;
import com.cs322.ors.service.TestService;

@RestController
@RequestMapping("/api/test")
@CrossOrigin
public class TestController {
	@Autowired
	private TestService testService;
	
	@GetMapping("/surfer")
	public String test1(){
		return "This is a surfer usecase. No auth";
	}
	@GetMapping("/customer")
	@PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
	//@PreAuthorize("#username == authentication.name")
	public String test2(){
		return "This is a customer usecase. You are login";
	}
	
	@GetMapping("/manager")
	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	//@PreAuthorize("#username == authentication.name")
	public String test3(){
		return "This is a manager usecase. You are login";
	}
	
	@GetMapping
	public List<Test> getAllTests(){
		return testService.getAllTests();
	}
	
	@GetMapping("/{id}")
	public Optional<Test> getTest(@PathVariable long id){
		return testService.getTestById(id);
	}
	
	@PostMapping
	public Test createTest(@RequestBody Test test){
		return testService.createTest(test);
	}
	
	@PutMapping("/{id}")
	public Test updateTest(@PathVariable long id, @RequestBody Test test){
		return testService.updateTest(id, test);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTest(@PathVariable long id){
		testService.deleteTest(id);
	}
	

}
