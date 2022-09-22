package com.skilldistillery.treattracker.services;

import java.util.List;

import com.skilldistillery.treattracker.entities.User;

public interface AuthService {
	public User register(User user);

	public User getUserByUsername(String username);

	public User getUserById(int userId);

	public User updateUserById(int userId, User user);
	
	public List<User> index(String username);

	public boolean deactivateUser(int userId);

	public boolean updateCredentials(int userId, String username, String password);

	public boolean deleteUser(int userId);
}
