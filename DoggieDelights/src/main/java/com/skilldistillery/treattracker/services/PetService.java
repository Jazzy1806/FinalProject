package com.skilldistillery.treattracker.services;

import java.util.List;

import com.skilldistillery.treattracker.entities.Pet;


public interface PetService {
    public List<Pet> index(String username);

    public Pet show(String username, int petId);

    public Pet create(String username, Pet pet);

    public Pet update(String username, int petId, Pet pet);

    public boolean destroy(String username, int petId);
}
