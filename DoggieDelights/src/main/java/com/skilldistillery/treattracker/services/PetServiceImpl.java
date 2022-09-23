package com.skilldistillery.treattracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Pet;
import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.repositories.PetRepository;
import com.skilldistillery.treattracker.repositories.UserRepository;

@Service
public class PetServiceImpl implements PetService{
	
	@Autowired
	private PetRepository petRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Pet> index(String username) {
		return petRepo.findByUser_Username(username);
	}

	@Override
	public Pet getPet(String username, int petId) {
		Optional<Pet> result = petRepo.findById(petId);
		if (result.isPresent()) {
			Pet pet= result.get();
			if (pet.getUser().getUsername().equals(username)) {
				return pet;
			}
		}
		return null;
	}

	@Override
	public Pet addPet(String username, Pet pet) {
		  User user = userRepo.findByUsername(username);
		  if (user != null) {
		    pet.setUser(user);
		    return petRepo.saveAndFlush(pet);
		  }
		  return null;
	}

	@Override
	public Pet updatePet(String username, int petId, Pet pet) {
		Optional<Pet> result = petRepo.findById(petId);
		if (result.isPresent()) {
			Pet udpatePet= result.get();
			if (udpatePet.getUser().getUsername().equals(username)) {
				udpatePet.setName(pet.getName());
				udpatePet.setWeight(pet.getWeight());
				udpatePet.setGender(pet.getGender());
				udpatePet.setImage(pet.getImage());
				udpatePet.setBirthDate(pet.getBirthDate());
				return petRepo.saveAndFlush(udpatePet);
			}
		}
		return null;
	}

	@Override
	public boolean deletePet(String username, int petId) {
		Optional<Pet> result = petRepo.findById(petId);
		if (result.isPresent()) {
			Pet pet= result.get();
			if (pet.getUser().getUsername().equals(username)) {
				pet.setEnabled(false);
				petRepo.saveAndFlush(pet);
				return true;
			}
		}
		return false;
	}


//	@Autowired
//	private PetRepository petRepo;
//	
//	@Autowired
//	private UserRepository userRepo;
//
//	
//	
//	@Override
//	public List<Pet> index(String username) {
//		return userRepo.findByUsername_Pets(username);
//	}
//
//	@Override
//	public Pet show(String username, int petId) {
//		Optional<Pet> result = petRepo.findById(petId);
//		if (result.isPresent()) {
//			Pet todo= result.get();
//			if (todo.getUser().getUsername().equals(username)) {
//				return todo;
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public Todo create(String username, Todo todo) {
//	  User user = userRepo.findByUsername(username);
//	  if (user != null) {
//	    todo.setUser(user);
//	    return todoRepo.saveAndFlush(todo);
//	  }
//	  return null;
//	}
//
//	@Override
//	public Todo update(String username, int tid, Todo todo) {
//		Optional<Todo> result = todoRepo.findById(tid);
//		if (result.isPresent()) {
//			Todo updateTodo= result.get();
//			if (updateTodo.getUser().getUsername().equals(username)) {
//				updateTodo.setTask(todo.getTask());
//				updateTodo.setDescription(todo.getDescription());
//				updateTodo.setCompleted(todo.getCompleted());
//				updateTodo.setDueDate(todo.getDueDate());
//				updateTodo.setCompleteDate(todo.getCompleteDate());
//				return todoRepo.saveAndFlush(updateTodo);
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public boolean destroy(String username, int tid) {
//		Optional<Todo> result = todoRepo.findById(tid);
//		if (result.isPresent()) {
//			Todo todo= result.get();
//			if (todo.getUser().getUsername().equals(username)) {
//				todoRepo.deleteById(tid);
//			}
//		}
//		boolean success = !todoRepo.existsById(tid);
//		return success;
//	}
}
