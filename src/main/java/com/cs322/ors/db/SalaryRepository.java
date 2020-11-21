package com.cs322.ors.db;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs322.ors.model.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long>{
	List<Salary> findByUser_Id(long user_id);
}

