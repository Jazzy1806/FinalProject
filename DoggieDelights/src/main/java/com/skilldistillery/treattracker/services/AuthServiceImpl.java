package com.skilldistillery.treattracker.services;

import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Address;
import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.repositories.AddressRepository;
import com.skilldistillery.treattracker.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AddressRepository addressRepo;

	@Override
	public User register(User user) throws SQLIntegrityConstraintViolationException {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("standard");
		Address address = user.getAddress();
		addressRepo.saveAndFlush(address);
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User getUserById(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		User user = null;
		if (userOpt.isPresent()) {
			user = userOpt.get();
			System.out.println(user.getUsername());
//			if (user.getUsername().equals(principal.getName())) {
				return user;
//			}
		}
		return null;
	}
	
	@Override
	public List<User> index(String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return userRepo.findAll();
		}
		return null;
	}

	@Override
	public User updateUserById(int userId, User user, Principal principal) {
		Optional<User> userOpt = userRepo.findById(userId);
		User updated = null;
		if (userOpt.isPresent()) {
			updated = userOpt.get();
			if (updated.getUsername().equals(principal.getName()) || userRepo.findByUsername(principal.getName()).getRole().equals("1")) {
				updated.setFirstName(user.getFirstName());
				updated.setLastName(user.getLastName());
				updated.setEmail(user.getEmail());
				Address newAdd;
				Optional<Address> addOpt = addressRepo.findById(user.getAddress().getId());
				if (addOpt.isPresent()) {
					newAdd = addOpt.get();
					newAdd.setAddress(user.getAddress().getAddress());
					newAdd.setCity(user.getAddress().getCity());
					newAdd.setState(user.getAddress().getState());
					newAdd.setPostalCode(user.getAddress().getPostalCode());
					newAdd.setPhone(user.getAddress().getPhone());
					System.out.println(newAdd);
					System.out.println(addressRepo.saveAndFlush(newAdd));
				}
				else {
					newAdd = addressRepo.saveAndFlush(user.getAddress());
					System.out.println(newAdd);
				}
				updated.setAddress(newAdd);
				updated.setBio(user.getBio());
				updated.setImage(user.getImage());
				updated.setRole(user.getRole());
				return userRepo.saveAndFlush(updated);
			}
		}
		return updated;
	}
	
///*
// * Map<Map<productId,price>, address>
// * List<Product> findProdBykeyword
//	List<Store>
//	for (Store store : Store) {
//	for Product prod : store.getProducts{
//	if prod.name contain keyword
//		most recent Price = getproductReportByProdId
	
	// Map.put(prod, most recentprice)}
	
// * Map 
//	}
// * List<Address> addresses
// * for (Store store : Store){
// * 	addresses.push (store.getAddress())
//	Map <ProductId, Price> map1
// * 
//	
// * List<Product> products index from product table
//
// * for (Product prod : Prod){
//		map1.setke
	
//
// * */

	@Override
	public boolean deactivateUser(int userId, Principal principal) {
		Optional<User> userOpt = userRepo.findById(userId);
		User updated = null;
		if (userOpt.isPresent()) {
			updated = userOpt.get();
			if (updated.getUsername().equals(principal.getName()) || userRepo.findByUsername(principal.getName()).getRole().equals("1")) {
				updated.setEnabled(false);
				userRepo.saveAndFlush(updated);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean activateUser(int userId, Principal principal) {
		Optional<User> userOpt = userRepo.findById(userId);
		User updated = null;
		if (userOpt.isPresent()) {
			updated = userOpt.get();
			if (updated.getUsername().equals(principal.getName()) || userRepo.findByUsername(principal.getName()).getRole().equals("1")) {
				updated.setEnabled(true);
				userRepo.saveAndFlush(updated);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateCredentials(int userId, User user, Principal principal) {
		Optional<User> userOpt = userRepo.findById(userId);
		User updated = null;
		if (userOpt.isPresent()) {
			updated = userOpt.get();
			if (updated.getUsername().equals(principal.getName()) || userRepo.findByUsername(principal.getName()).getRole().equals("1")) {
				updated.setUsername(user.getUsername());
				updated.setPassword(encoder.encode(user.getPassword()));
				userRepo.saveAndFlush(updated);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteUser(int userId, Principal principal) {
		Optional<User> userOpt = userRepo.findById(userId);
		if (userOpt.isPresent()) {
			if (userRepo.findByUsername(principal.getName()).getRole().equals("1")) {
				userRepo.deleteById(userId);
				return true;
			}
		}
		return false;
	}
	
	
}
