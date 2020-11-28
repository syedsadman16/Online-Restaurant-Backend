package com.cs322.ors.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cs322.ors.model.User;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.TransactionService;
import com.cs322.ors.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	TransactionService transactionService;

	@GetMapping
	@PreAuthorize("hasRole('MANAGER')")
	public List<User> accessUsers() {
		return userService.getAllUsers();
	}

	@PostMapping
	public User createAccount(@Valid @RequestBody User newUser) {
		return userService.createUser(newUser);
	}

	@GetMapping("/{role}")
	public List<User> findByRole(@PathVariable("role") String role){
		return userService.findUserByRole(role);
	}

	@GetMapping("/{id}")
	@PreAuthorize("#id == principal.user.id OR hasRole('MANAGER')") // Users can only access their account OR manager
	public Optional<User> accessUserInfo(@PathVariable long id, Authentication authUser) {
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		return userService.getUserById(id);
	}

	@GetMapping("/balance")
	@PreAuthorize("hasAnyRole('CUSTOMER','VIP')")
	public Map<String,BigDecimal>  getBalance(Authentication authUser) {
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		if (!currentUser.isClosed()) {
			Map<String,BigDecimal> balance = new HashMap<>();
			balance.put("balance", transactionService.getTransactionSumByCustomer(currentUser));
			return balance;

		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account closed");

	}
	@PatchMapping("/{id}")
	@PreAuthorize("#id == principal.user.id OR hasRole('MANAGER')") // Users can only access their account OR manager
	public User updateUserInfo(@PathVariable long id, @RequestBody Map<Object, Object> patchedUser,
			Authentication authUser) {
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		if (!currentUser.isClosed()) {
			return userService.patchUser(id, patchedUser);
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN);

	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('MANAGER')") // Only manager has access
	public void deleteAccount(@PathVariable long id) {
		userService.deleteUser(id);
	}
}
