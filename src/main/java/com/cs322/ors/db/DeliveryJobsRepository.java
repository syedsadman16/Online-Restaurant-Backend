package com.cs322.ors.db;

import com.cs322.ors.model.DeliveryJobs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryJobsRepository extends JpaRepository<DeliveryJobs, Long>{
    List<DeliveryJobs> findByDeliveryJobsStatus(@Param(value = "status") int status);
}
