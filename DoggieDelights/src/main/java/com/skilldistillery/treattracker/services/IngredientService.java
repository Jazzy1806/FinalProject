package com.skilldistillery.treattracker.services;

import java.util.List;

import com.skilldistillery.treattracker.entities.Ingredient;

public interface IngredientService {

	public List<Ingredient> index(String username, int pid);

	// public ProductReport show(String username, int pid);

	public Ingredient create(String username, Ingredient ingredient);

	public Ingredient update(String username, int iid, Ingredient ingredient);

	public boolean destroy(String username, int iid);
}