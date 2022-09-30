package com.skilldistillery.treattracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Ingredient;
import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.repositories.IngredientRepository;
import com.skilldistillery.treattracker.repositories.UserRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientRepository ingredientRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Ingredient> index(String username, int pid) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return ingredientRepo.findByProductsId(pid);
		}
		return null;
	}
	
	@Override
	public List<Ingredient> getAll(String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return ingredientRepo.findAll();
		}
		return null;
	}

//	@Override
//	public ProductReport show(String username, int pid) {
//		User user = userRepo.findByUsername(username);
//		Optional<ProductReport> prodOpt = prodRepo.findById(pid);
//		if (prodOpt.isPresent()) {
//			ProductReport product = prodOpt.get();
//			if (user != null) {
//				return product;
//			}
//		}
//		return null;
//	}

	@Override
	public Ingredient create(String username, Ingredient ingredient) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return ingredientRepo.saveAndFlush(ingredient);
		}
		return null;
	}

	@Override
	public Ingredient update(String username, int iid, Ingredient ingredient) {
		User user = userRepo.findByUsername(username);
		Optional<Ingredient> reportOpt = ingredientRepo.findById(iid);
		if (reportOpt.isPresent()) {
//			Ingredient updated = reportOpt.get();
			if (user != null) {
				ingredientRepo.save(ingredient);
				return ingredient;
			}
		}
		return null;
	}

	@Override
	public boolean destroy(String username, int iid) {
		User user = userRepo.findByUsername(username);
		Optional<Ingredient> opt = ingredientRepo.findById(iid);
		if (opt.isPresent()) {
			if (user != null) {
				try {
					ingredientRepo.deleteById(iid);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}