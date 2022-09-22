package com.skilldistillery.treattracker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Pet;

@Service
public class PetServiceImpl implements PetService{

	@Override
	public List<Pet> index(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pet show(String username, int petId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pet create(String username, Pet pet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pet update(String username, int petId, Pet pet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean destroy(String username, int petId) {
		// TODO Auto-generated method stub
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
