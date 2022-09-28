package com.skilldistillery.treattracker.controllers;

import java.security.Principal;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.services.AuthService;

@RestController
@CrossOrigin({ "*", "http://localhost:4205" })
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("register")
	public User register(@RequestBody User user, HttpServletResponse res) {
		if (user == null) {
			res.setStatus(400);
			return null;
		}
		try {
			user = authService.register(user);
		} catch (DataIntegrityViolationException e) {
			System.out.println("cause" + e.getCause());
			e.printStackTrace();
			res.setStatus(409);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName());
			e.printStackTrace();
			res.setStatus(400);
		}
		return user;
	}

	@GetMapping("authenticate")
	public User authenticate(Principal principal, HttpServletResponse res) {
		if (principal == null) { // no Authorization header sent
			res.setStatus(401);
			res.setHeader("WWW-Authenticate", "Basic");
			return null;
		}
		return authService.getUserByUsername(principal.getName());
	}
}
