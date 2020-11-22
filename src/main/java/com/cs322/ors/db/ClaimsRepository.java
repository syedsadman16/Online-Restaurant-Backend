package com.cs322.ors.db;

import com.cs322.ors.model.Claims;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimsRepository extends JpaRepository<Claims, Long> {
}
