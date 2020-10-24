package com.cs322.ors.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs322.ors.model.DishOrder;

public interface DishOrderRepository extends JpaRepository<DishOrder, Long> {

}
