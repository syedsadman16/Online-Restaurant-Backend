package com.cs322.ors.db;

import com.cs322.ors.model.User;
import com.cs322.ors.model.UserRatings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRatingsRepository extends JpaRepository<UserRatings, Long> {
    List<UserRatings> findAllByPerson(User person);
}
