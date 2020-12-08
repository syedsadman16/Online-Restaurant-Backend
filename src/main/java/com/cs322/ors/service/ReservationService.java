package com.cs322.ors.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cs322.ors.db.ReservationRepository;
import com.cs322.ors.model.Order;
import com.cs322.ors.model.Reservation;
import com.cs322.ors.model.RestaurantTable;
import com.cs322.ors.model.TimeSlot;

@Service
public class ReservationService {

	@Autowired
	public ReservationRepository reservationRepository;

	public Reservation createReservation(Reservation reservation) {

		checkValidTimeSlot(reservation.getTimeSlot());
		Boolean isAvailable = isReservationAvailable(reservation.getTable(), reservation.getTimeSlot());
		if (isAvailable) {
			return reservationRepository.save(reservation);
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Table has already reserved.");

	}
	
	public List<Reservation> getReservationByUser(long id) {
		return reservationRepository.findByCustomerId(id);
	}

	public boolean isReservationAvailable(RestaurantTable table, TimeSlot timeSlot) {
		long countOfConflictedRecords = reservationRepository.countOfConflictedReservations(table.getId(),
				timeSlot.getFrom(), timeSlot.getTo());
		return countOfConflictedRecords <= 0;
	}

	public List<Reservation> findReservationByTableId(RestaurantTable table) {
		List<Reservation> reservations = reservationRepository.findByTableId(table.getId());
		return reservations;
	}

	public void checkValidTimeSlot(TimeSlot timeSlot) {
		Boolean compare = timeSlot.getTo().isAfter(timeSlot.getFrom());
		if (compare) {
			return;
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date ("
				+ timeSlot.getFrom() + ") must less than to date (" + timeSlot.getTo() + ")");
	}

}
