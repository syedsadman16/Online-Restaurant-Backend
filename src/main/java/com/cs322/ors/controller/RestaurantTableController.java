package com.cs322.ors.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs322.ors.model.RestaurantTable;
import com.cs322.ors.model.TimeSlot;
import com.cs322.ors.service.RestaurantTableService;


@RestController
@RequestMapping("/api/tables")
public class RestaurantTableController {

	@Autowired
	RestaurantTableService restaurantTableService;

	@GetMapping
	public List<RestaurantTable> getAvailableTables(@RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
    LocalDateTime to, @RequestParam("from")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
    LocalDateTime from) {
		return restaurantTableService.findAvailableTable(new TimeSlot(from,to));
	}

}
