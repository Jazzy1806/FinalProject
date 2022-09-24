package com.skilldistillery.treattracker.services;

import java.util.List;

import com.skilldistillery.treattracker.entities.Product;

public interface ProductService {

	public List<Product> index(String username);

	public Product show(String username, int pid);

	public Product create(String username, Product product);

	public Product update(String username, int tid, Product product);

	public boolean destroy(String username, int tid);
	
	List<Product> findByKeyword(String username, String keyword);
}