package com.skilldistillery.treattracker.services;

import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.skilldistillery.treattracker.entities.User;

public interface AuthService {
	public User register(User user) throws SQLIntegrityConstraintViolationException;

	public User getUserByUsername(String username);

	public User getUserById(int userId);

	public User updateUserById(int userId, User user, Principal principal);
	
	public List<User> index(String username);

	public boolean deactivateUser(int userId, Principal principal);

	public boolean activateUser(int userId, Principal principal);

	public boolean updateCredentials(int userId, User user, Principal principal);

	public boolean deleteUser(int userId, Principal principal);
}
