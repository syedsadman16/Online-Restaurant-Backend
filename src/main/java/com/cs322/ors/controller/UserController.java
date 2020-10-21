package com.cs322.ors.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs322.ors.model.User;
import com.cs322.ors.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping
	@PreAuthorize("hasRole('MANAGER')")
	public List<User> accessUsers() {
		return userService.getAllUsers();
	}
		
	@PostMapping
	public User createAccount(@Valid @RequestBody User newUser) {
		return userService.createUser(newUser);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("(!hasRole('MANAGER') AND #id == principal.user.id ) OR hasRole('MANAGER')")
	public Optional<User> accessUsersInfo(@PathVariable long id) {
		return userService.getUserById(id);
	}

	@PatchMapping("/{id}")
	@PreAuthorize("#id == principal.user.id")
	public User updateUserInfo(@PathVariable long id, @RequestBody Map<Object, Object> patchedUser) {
		return userService.patchUser(id, patchedUser);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('MANAGER')")
	public void deleteAccount(@PathVariable long id) {
		userService.deleteUser(id);
	}
}
