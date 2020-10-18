package com.cs322.ors.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs322.ors.model.User;

public interface userRepository extends JpaRepository<User, Long> {

}
//*