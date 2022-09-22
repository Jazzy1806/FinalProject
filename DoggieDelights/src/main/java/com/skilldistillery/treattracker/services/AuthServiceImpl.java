package com.skilldistillery.treattracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("standard");
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
		}
		return user;
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
	public User updateUserById(int userId, User user) {
		Optional<User> userOpt = userRepo.findById(userId);
		User updated = null;
		if (userOpt.isPresent()) {
			updated = userOpt.get();
			updated.setFirstName(user.getFirstName());
			updated.setLastName(user.getLastName());
			updated.setEmail(user.getEmail());
			updated.setBio(user.getBio());
			updated.setImage(user.getImage());
			return userRepo.saveAndFlush(updated);
		}
		return updated;
	}

	@Override
	public boolean deactivateUser(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		User updated = null;
		if (userOpt.isPresent()) {
			updated = userOpt.get();
			updated.setEnabled(false);
			userRepo.saveAndFlush(updated);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateCredentials(int userId, String username, String password) {
		Optional<User> userOpt = userRepo.findById(userId);
		User updated = null;
		if (userOpt.isPresent()) {
			updated = userOpt.get();
			updated.setUsername(username);
			updated.setPassword(encoder.encode(password));
			userRepo.saveAndFlush(updated);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		User updated = null;
		if (userOpt.isPresent()) {
			updated = userOpt.get();
			userRepo.deleteById(userId);
			return true;
		}
		return false;
	}
	
	
}
