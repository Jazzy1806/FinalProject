package com.skilldistillery.treattracker.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.services.AuthService;
import com.skilldistillery.treattracker.services.PetService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class UserController {

	@Autowired
	private PetService petService;
	
	@Autowired
	private AuthService authService;
	
	
//	GET  /users      get all users
	@GetMapping("users")
	public List<User> index(HttpServletRequest req, HttpServletResponse res, Principal principal ) {
		return authService.index(principal.getName()); 
	}
	
//	GET /users/{userId}      findUserById
	@GetMapping("users/{userId}")
	public User findById(@PathVariable int userId, HttpServletRequest req, HttpServletResponse res) {
		User user = authService.getUserById(userId); 
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}
	
//	GET  /users/{username}      find user by username
	@GetMapping("users/{username}")
	public User findByUsername(@PathVariable String username, HttpServletRequest req, HttpServletResponse res) {
		User user = authService.getUserByUsername(username); 
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}

	
////	POST  /users          register new user - don't need- duplicate from auth

	
//	PUT     /users/{userId}      update user
	@PutMapping("users/{userId}")
	public User update(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, @RequestBody User user) {
		User updated = null;
		try {
		updated = authService.updateUserById(userId, user); 
		}
		catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updated;
	}
	
//	PUT    /users/{userId}      update user to deactivate (delete)-serviceImpl
	@PutMapping(value="users/{userId}", params = "userId")
	public boolean deactivate(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId) {
		boolean result=false;
		try {
			result = authService.deactivateUser(userId); 
		}
		catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return result;
	}

	//	PUT    /users/{userId}      update user credentials
	@PutMapping(value="users/{userId}", params= {"userId", "username", "password"})
	public boolean updateCredentials(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, @RequestBody String username, @RequestBody String password) {
		boolean result=false;
		try {
			result = authService.updateCredentials(userId, username, password); 
		}
		catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return result;
	}

	
	
//	DELETE    /users/{userId}       delete user?- -Admin only
	@DeleteMapping("users/{userId}")
	public boolean destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId) { 
		boolean deleted = authService.deleteUser(userId);
		if (deleted) {
			res.setStatus(200);
		}
		else {
			res.setStatus(400);
		}
		return deleted;
	}
	

////	GET   /users/{userId}/pets    get all user pets
//	@GetMapping("todos")
//	public Set<Todo> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
//		return todoServe.index(principal.getName()); 
//	}
//	
//	@GetMapping("todos/{tid}")
//	public Todo show(@PathVariable int tid, HttpServletRequest req, HttpServletResponse res, Principal principal) {
//		Todo todo = todoServe.show(principal.getName(), tid); 
//		if (todo == null) {
//			res.setStatus(404);
//		}
//		return todo;
//	}
//	
//	@PostMapping("todos")
//	public Todo create(HttpServletRequest req, HttpServletResponse res, @RequestBody Todo todo, Principal principal) {
//		Todo created = null;
//		try {
//		created = todoServe.create(principal.getName(), todo); 
//		res.setStatus(201);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			res.setStatus(400);
//		}
//		return created;
//	}
//	
//	@PutMapping("todos/{tid}")
//	public Todo update(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid, @RequestBody Todo todo, Principal principal) {
//		Todo updated = null;
//		try {
//		updated = todoServe.update(principal.getName(), tid, todo); 
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			res.setStatus(400);
//		}
//		return updated;
//	}
//	
//	@DeleteMapping("todos/{tid}")
//	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid, Principal principal) { 
//		boolean deleted = todoServe.destroy(principal.getName(), tid);
//		if (deleted) {
//			res.setStatus(200);
//		}
//		else {
//			res.setStatus(400);
//		}
//	}
//	GET  /users      get all users
//
//	GET /users/{userId}      findUserById
//
//	GET  /users/{username}      find user by username
//
//	POST  /users          register new user
//
//	PUT     /users/{userId}      update user
//
//	PUT    /users/{userId}      update user to deactivate (delete)-serviceImpl
//
//	DELETE    /users/{userId}       delete user?- -Admin only
//
//	GET   /users/{userId}/pets    get all user pets
//
//	GET    /users/{userId}/pets/{petId}    get specific pet
//
//	POST  /users/{userId}/pets          register new pet
//
//	PUT     /users/{userId}/pets/{petId}      update user
//
//	PUT    /users/{userId}/pets/{petId}      update user to deactivate (delete)-serviceImpl
//
//
//
//	GET /users/[userId}/addresses/{addressId}  find address for user
//
//	POST /users/[userId}/addresses/        create address for user
//
//	PUT  /users/[userId}/addresses/{addressId}  update address for user


}
