package com.skilldistillery.treattracker.services;

import com.skilldistillery.treattracker.entities.User;

public interface AuthService {
	public User register(User user);

	public User getUserByUsername(String username);

	User getUserById(int userId);
}
