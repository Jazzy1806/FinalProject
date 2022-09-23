package com.skilldistillery.treattracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {
	public Pet getUserById(int userId);

	public Pet findByName(String username);
	
	public List<Pet> findByUser_Username(String username);
}
