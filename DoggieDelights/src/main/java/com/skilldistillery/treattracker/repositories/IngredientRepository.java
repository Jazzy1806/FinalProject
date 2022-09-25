package com.skilldistillery.treattracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

	public List<Ingredient> findByProductsId(int pid);

	public Ingredient findByIdAndProductsId(int rid, int pid);

//	public List<Ingredient> findByProduct_Id(int iid);

}
