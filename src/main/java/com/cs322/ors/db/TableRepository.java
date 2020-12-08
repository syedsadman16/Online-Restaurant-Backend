package com.cs322.ors.db;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs322.ors.model.RestaurantTable;



@Repository
public interface TableRepository extends JpaRepository<RestaurantTable, Long>{
	
}

