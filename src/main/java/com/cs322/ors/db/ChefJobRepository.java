package com.cs322.ors.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs322.ors.model.ChefJob;

public interface ChefJobRepository extends JpaRepository<ChefJob, Long> {

}
