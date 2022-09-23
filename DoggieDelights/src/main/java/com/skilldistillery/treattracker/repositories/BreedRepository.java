package com.skilldistillery.treattracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Breed;

public interface BreedRepository extends JpaRepository<Breed, Integer> {
	Breed getBreedById(int userId);

	Breed findByName(String username);
}
