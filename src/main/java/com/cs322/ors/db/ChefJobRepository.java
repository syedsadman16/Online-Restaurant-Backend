package com.cs322.ors.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cs322.ors.model.ChefJob;

@Repository
public interface ChefJobRepository extends JpaRepository<ChefJob, Long> {
	
	List<ChefJob> findByCompleted(boolean status);
	
	@Query("SELECT c FROM ChefJob c where c.chef.id = :id AND c.completed = :status")
	List<ChefJob> findByUser_idAndStatus(@Param("id")long id,
										@Param("status") boolean status);
	
}
