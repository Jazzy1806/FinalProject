package com.skilldistillery.treattracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Breed;
import com.skilldistillery.treattracker.entities.Diet;
import com.skilldistillery.treattracker.entities.Pet;
import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.repositories.BreedRepository;
import com.skilldistillery.treattracker.repositories.DietRepository;
import com.skilldistillery.treattracker.repositories.PetRepository;
import com.skilldistillery.treattracker.repositories.UserRepository;

@Service
public class PetServiceImpl implements PetService{
	
	@Autowired
	private PetRepository petRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BreedRepository breedRepo;
	
	@Autowired
	private DietRepository dietRepo;

	@Override
	public List<Pet> index(String username) {
		return petRepo.findByUser_Username(username);
	}

	@Override
	public Pet getPet(String username, int petId) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Optional<Pet> result = petRepo.findById(petId);
			if (result.isPresent()) {
				Pet pet= result.get();
				if (pet.getUser().getUsername().equals(username)) {
					return pet;
				}
			}
		}
		return null;
	}

	@Override
	public Pet addPet(String username, Pet pet) {
		  User user = userRepo.findByUsername(username);
		  if (user != null) {
		    pet.setUser(user);
		    pet.setEnabled(true);
		    return petRepo.saveAndFlush(pet);
		  }
		  return null;
	}

	@Override
	public List<Breed> getBreeds() {
		return breedRepo.findAll();
	}

	@Override
	public List<Diet> getDiets() {
		return dietRepo.findAll();
	}




	@Override
	public Pet updatePet(String username, int petId, Pet pet) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Optional<Pet> result = petRepo.findById(petId);
			if (result.isPresent()) {
				Pet udpatePet= result.get();
				if (udpatePet.getUser().getUsername().equals(username)) {
					udpatePet.setName(pet.getName());
					udpatePet.setWeight(pet.getWeight());
					udpatePet.setGender(pet.getGender());
					udpatePet.setImage(pet.getImage());
					udpatePet.setBirthDate(pet.getBirthDate());
					udpatePet.setBreeds(pet.getBreeds());
					udpatePet.setDietNeeds(pet.getDietNeeds());
					udpatePet.setEnabled(pet.getEnabled());
					return petRepo.saveAndFlush(udpatePet);
				}
			}
		}
		return null;
	}

	@Override
	public boolean deletePet(String username, int petId) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Optional<Pet> result = petRepo.findById(petId);
			if (result.isPresent()) {
				Pet pet= result.get();
				if (pet.getUser().getUsername().equals(username)) {
					pet.setEnabled(false);
					petRepo.saveAndFlush(pet);
					return true;
				}
			}
		}
		return false;
	}

}
