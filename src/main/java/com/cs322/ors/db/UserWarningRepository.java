package com.cs322.ors.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs322.ors.model.Order;
import com.cs322.ors.model.UserWarning;

@Repository
public interface UserWarningRepository extends JpaRepository<UserWarning, Long>{
	List<UserWarning> findByUser_Id(long id);
	void deleteByUser_Id(long id);
}
