package com.cs322.ors.db;

import com.cs322.ors.model.Claims;
import com.cs322.ors.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimsRepository extends JpaRepository<Claims, Long> {
    List<Claims> findByVictim(User victim);
}
