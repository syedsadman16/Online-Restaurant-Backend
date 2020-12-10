package com.cs322.ors.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.RestaurantTableRepository;
import com.cs322.ors.model.RestaurantTable;
import com.cs322.ors.model.TimeSlot;

@Service
public class RestaurantTableService {
	
	@Autowired
	RestaurantTableRepository restaurantTableRepository;
	
	public List<RestaurantTable> findAvailableTable(TimeSlot timeSlot){
		return restaurantTableRepository.findAvailableTables(timeSlot.getFrom(), timeSlot.getTo());
	}
	
	public RestaurantTable findTableByName(Long name){
		return restaurantTableRepository.findByName(name);
	}
	

}
