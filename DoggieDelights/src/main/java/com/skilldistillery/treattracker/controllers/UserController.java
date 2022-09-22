package com.skilldistillery.treattracker.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class UserController {
	GET  /users      get all users

	GET /users/{userId}      findUserById

	GET  /users/{username}      find user by username

	POST  /users          register new user

	PUT     /users/{userId}      update user

	PUT    /users/{userId}      update user to deactivate (delete)-serviceImpl

	DELETE    /users/{userId}       delete user?- -Admin only

	GET   /users/{userId}/pets    get all user pets

	GET    /users/{userId}/pets/{petId}    get specific pet

	POST  /users/{userId}/pets          register new pet

	PUT     /users/{userId}/pets/{petId}      update user

	PUT    /users/{userId}/pets/{petId}      update user to deactivate (delete)-serviceImpl



	GET /users/[userId}/addresses/{addressId}  find address for user

	POST /users/[userId}/addresses/        create address for user

	PUT  /users/[userId}/addresses/{addressId}  update address for user

}
