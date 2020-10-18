package com.cs322.ors.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class UserController{


	@GetMapping("/") 
		public String home(){
			return ("<h1> Welcome <h1>");
		}
	@GetMapping("/manager")
	public String manager() {
		return ("<h1> Welcome manager <h1>");		
	}
	

	@GetMapping("/user")
		public String user(){
			return("<h1>Welcome User<h1>");
		}
}
	
