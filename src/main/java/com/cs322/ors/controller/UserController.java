package com.cs322.ors.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cs322.ors.service.*;
@RestController
@RequestMapping("/user")

public class UserController{
    @Autowired
    private userService userService;

    @GetMapping("/user")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
	public Optional<Test> getUser(@PathVariable long id){
		return testService.getUserId(id);
    }
    
	@PostMapping("/user")
	public User newUser(@RequestBody User user){
		return userService.createUser(user);
	}
	
	@PutMapping("/user/{id}")
	public User updateTest(@PathVariable long id, @RequestBody User user){
		return userService.updateUser(id, user);
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteTest(@PathVariable long id){
		userService.deleteUser(id);
	}
}