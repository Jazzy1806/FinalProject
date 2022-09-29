package com.skilldistillery.treattracker.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String message;
	private int fromLogin;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getFromLogin() {
		return fromLogin;
	}
	
	public void setFromLogin(int fromLogin) {
		this.fromLogin = fromLogin;
	}
	
	@Override
	public String toString() {
		return "Message [message=" + message + ", fromLogin=" + fromLogin + "]";
	}
}