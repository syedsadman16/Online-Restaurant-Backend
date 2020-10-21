package com.cs322.ors.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs322.ors.security.UserPrincipal;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

	@GetMapping
	@PreAuthorize("hasRole('MANAGER')")
	public String accessUsers(Principal user) {
		
		return ("<h1> user info <h1>");
	}
		
	@PostMapping
	public String createAccount() {
		return ("<h1> new user <h1>");
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("#id == principal.user.username or hasRole('MANAGER')")
	public String accessUsersInfo(Principal a) {
		//System.out.println(((UserPrincipal)((UsernamePasswordAuthenticationToken)a).getPrincipal()).user);
		return ("<h1> Welcome <h1>");
	}

	@PatchMapping("/{id}")
	@PreAuthorize("#id == principal.user.username")
	public String updateUserInfo() {
		return ("<h1> Welcome <h1>");
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('MANAGER')")
	public String deleteAccount() {
		return ("<h1> Welcome <h1>");
	}
}
