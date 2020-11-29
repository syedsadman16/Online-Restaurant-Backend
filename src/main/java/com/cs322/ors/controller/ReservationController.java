package com.cs322.ors.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs322.ors.model.Comment;
import com.cs322.ors.model.Reservation;
import com.cs322.ors.model.User;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.CommentService;
import com.cs322.ors.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;

	
	@GetMapping
	@PreAuthorize("hasAnyRole('CUSTOMER', 'VIP')")
	public List<Reservation> getReservation( Authentication authUser){
			User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();	
			return reservationService.getReservationByUser(currentUser.getId());
	}
	
	
}

