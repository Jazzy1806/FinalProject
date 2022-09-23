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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.treattracker.entities.Address;
import com.skilldistillery.treattracker.entities.Pet;
import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.services.AddressService;
import com.skilldistillery.treattracker.services.AuthService;
import com.skilldistillery.treattracker.services.PetService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class UserController {

	@Autowired
	private PetService petService;

	@Autowired
	private AddressService addService;
	
	@Autowired
	private AuthService authService;
	
	
	@GetMapping("users")
	public List<User> index(HttpServletRequest req, HttpServletResponse res, Principal principal ) {
		return authService.index(principal.getName()); 
	}
	
	
	@GetMapping("users/{userId}")
	public User findById(@PathVariable int userId, HttpServletRequest req, HttpServletResponse res) {
		User user = authService.getUserById(userId); 
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}
	
	
	@GetMapping("users/keyword/{username}")
	public User findByUsername(@PathVariable String username, HttpServletRequest req, HttpServletResponse res) {
		User user = authService.getUserByUsername(username); 
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}

	
	@PutMapping("users/{userId}")
	public User update(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, @RequestBody User user, Principal principal) {
		User updated = null;
		try {
		updated = authService.updateUserById(userId, user, principal); 
		}
		catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updated;
	}
	
	
	@PutMapping("users/deactivate/{userId}")
	public boolean deactivate(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, Principal principal) {
		boolean result=false;
		try {
			result = authService.deactivateUser(userId, principal); 
		}
		catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return result;
	}
	
	
	@PutMapping("users/activate/{userId}")
	public boolean activate(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, Principal principal) {
		boolean result=false;
		try {
			result = authService.activateUser(userId, principal); 
		}
		catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return result;
	}

	
	@PutMapping("users/credentials/{userId}")
	public boolean updateCredentials(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, @RequestBody User user, Principal principal) {
		boolean result=false;
		try {
			result = authService.updateCredentials(userId, user, principal); 
		}
		catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return result;
	}

	
	@DeleteMapping("users/{userId}")
	public boolean deleteUser(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, Principal principal) { 
		boolean deleted = authService.deleteUser(userId, principal);
		if (deleted) {
			res.setStatus(200);
		}
		else {
			res.setStatus(400);
		}
		return deleted;
	}
	

	@GetMapping("pets")
	public List<Pet> findPetsByUser(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return petService.index(principal.getName()); 
	}
	
	@GetMapping("pets/{petId}")
	public Pet findPet(@PathVariable int petId, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		Pet pet = petService.getPet(principal.getName(), petId); 
		if (pet == null) {
			res.setStatus(404);
		}
		return pet;
	}

	@PostMapping("pets")
	public Pet addPet(HttpServletRequest req, HttpServletResponse res, @RequestBody Pet pet, Principal principal) {
		Pet created = null;
		try {
		created = petService.addPet(principal.getName(), pet); 
		res.setStatus(201);
		}
		catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return created;
	}
	
	@PutMapping("pets/{petId}")
	public Pet update(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, @RequestBody Pet pet, Principal principal) {
		Pet updated = null;
		try {
		updated = petService.updatePet(principal.getName(), petId, pet); 
		}
		catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updated;
	}
	
	@DeleteMapping("pets/{petId}")
	public boolean deletePet(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, Principal principal) { 
		boolean deleted = petService.deletePet(principal.getName(), petId);
		if (deleted) {
			res.setStatus(200);
		}
		else {
			res.setStatus(400);
		}
		return deleted;
	}

	
	@GetMapping("addresses/{addressId}")
	public Address getUserAddress(@PathVariable int addressId, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		Address address = addService.getAddress(principal.getName(), addressId); 
		if (address == null) {
			res.setStatus(404);
		}
		return address;
	}
	
	@PostMapping("addresses")
	public Address create(HttpServletRequest req, HttpServletResponse res, @RequestBody Address address, Principal principal) {
		Address created = null;
		try {
		created = addService.addAddress(principal.getName(), address); 
		res.setStatus(201);
		}
		catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return created;
	}
	

	@PutMapping("addresses/{addressId}")
	public Address update(HttpServletRequest req, HttpServletResponse res, @PathVariable int addressId, @RequestBody Address address, Principal principal) {
		Address updated = null;
		try {
		updated = addService.updateAddress(principal.getName(), addressId, address); 
		}
		catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updated;
	}
}
