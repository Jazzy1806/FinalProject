package com.skilldistillery.treattracker.services;

import java.util.List;

import com.skilldistillery.treattracker.entities.Pet;


public interface PetService {
    public List<Pet> index(String username);

    public Pet getPet(String username, int petId);

    public Pet addPet(String username, Pet pet);

    public Pet updatePet(String username, int petId, Pet pet);

    public boolean deletePet(String username, int petId);
}
