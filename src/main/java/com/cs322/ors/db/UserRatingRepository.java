package com.cs322.ors.db;

import com.cs322.ors.model.User;
import com.cs322.ors.model.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRatingRepository extends JpaRepository<UserRating, Long> {
   // List<UserRating> findByUserCritic(User critic);
}
