package com.cs322.ors.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs322.ors.model.DeliveryStatus;
import com.cs322.ors.model.Discussion;

@Repository
 public interface  DiscussionRepository extends JpaRepository <Discussion,Long>{

}
