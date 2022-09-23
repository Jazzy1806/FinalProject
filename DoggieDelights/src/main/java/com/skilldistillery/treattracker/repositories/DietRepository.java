package com.skilldistillery.treattracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Diet;

public interface DietRepository extends JpaRepository<Diet, Integer> {
	Diet getDietById(int userId);

	Diet findByName(String username);
}
