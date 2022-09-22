package com.skilldistillery.treattracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Pet;
import com.skilldistillery.treattracker.entities.User;

public interface PetRepository extends JpaRepository<Pet, Integer> {
	Pet getUserById(int userId);

	Pet findByName(String username);
}
