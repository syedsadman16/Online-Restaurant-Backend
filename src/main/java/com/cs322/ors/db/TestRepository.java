package com.cs322.ors.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs322.ors.model.Test;

public interface TestRepository extends JpaRepository<Test, Long> {

}
