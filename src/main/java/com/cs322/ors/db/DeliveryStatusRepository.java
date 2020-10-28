package com.cs322.ors.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs322.ors.model.DeliveryStatus;

public interface DeliveryStatusRepository extends JpaRepository <DeliveryStatus,Long>{

}
