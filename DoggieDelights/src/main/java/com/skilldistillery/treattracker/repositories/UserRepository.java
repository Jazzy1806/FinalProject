package com.skilldistillery.treattracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User getUserById(int userId);

	User findByUsername(String username);

}
