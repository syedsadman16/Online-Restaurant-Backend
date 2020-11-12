package com.cs322.ors.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs322.ors.model.Order;
import com.cs322.ors.model.UserWarning;

@Repository
public interface UserWarningRepository extends JpaRepository<UserWarning, Long>{
	List<UserWarning> findByCustomer_Id(long id);
	void deleteByCustomer_Id(long id);
}
