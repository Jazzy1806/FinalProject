package com.skilldistillery.treattracker.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Ingredient;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.ProductComment;
import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.repositories.ProductRepository;
import com.skilldistillery.treattracker.repositories.UserRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository prodRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Product> index(String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return prodRepo.findAll();
		}
		return null;
	}
	@Override
	public List<Product> index() {
		return prodRepo.findAll();
		
	}

	@Override
	public Product show(String username, int pid) {
		User user = userRepo.findByUsername(username);
		Optional<Product> prodOpt = prodRepo.findById(pid);
		if (prodOpt.isPresent()) {
			Product product = prodOpt.get();
			if (user != null) {
				return product;
			}
		}
		return null;
	}

	@Override
	public List<Product> findByKeyword(String username, String keyword) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return prodRepo.findByNameIgnoreCaseLikeOrBrandIgnoreCaseLikeOrDescriptionIgnoreCaseLikeOrderByNameAsc(
					"%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
		}
		return null;
	}

	@Override
	public Product create(String username, Product product) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return prodRepo.saveAndFlush(product);
		}
		return null;
	}

	@Override
	public Product update(String username, int pid, Product product) {
		User user = userRepo.findByUsername(username);
		Optional<Product> prodOpt = prodRepo.findById(pid);
		if (prodOpt.isPresent()) {
			Product prodUpdate = prodOpt.get();
			if (user != null) {
				prodUpdate.setName(product.getName());
				prodUpdate.setBrand(product.getBrand());
				prodUpdate.setDescription(product.getDescription());
				prodUpdate.setImage(product.getImage());
				prodUpdate.setDateUpdated(LocalDateTime.now());
				prodUpdate.setEnabled(product.getEnabled());
				prodUpdate.setReports(null);

				prodRepo.save(prodUpdate);
				return prodUpdate;
			}
		}
		return null;
	}

	@Override
	public boolean destroy(String username, int pid) {
		User user = userRepo.findByUsername(username);
		Optional<Product> prodOpt = prodRepo.findById(pid);
		if (prodOpt.isPresent()) {
			if (user != null) {
				Product prodToDel = prodOpt.get();

				List<ProductComment> comments = prodToDel.getComments();
				if (comments.size() > 0) {
					for (ProductComment comment : comments) {
						comment.removeComments();
					}
				}

				List<Ingredient> ingredients = prodToDel.getIngredients();
				if (ingredients.size() > 0) {
					prodToDel.removeIngredients();
				}

				// delete inventory from product
				// delete inventory from store
				// delete inventory

				prodRepo.saveAndFlush(prodToDel);

				try {
					prodRepo.deleteById(pid);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public Product findById(String username, int prodId) {
		User user = userRepo.findByUsername(username);
		Optional<Product> prodOp = prodRepo.findById(prodId);
		if (prodOp.isPresent()) {
			Product prod = prodOp.get();
			if (user != null) {
				return prod;
			}
		}
		
		return null;
	}
}