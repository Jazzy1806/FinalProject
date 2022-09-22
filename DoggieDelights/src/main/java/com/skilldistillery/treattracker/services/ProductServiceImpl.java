package com.skilldistillery.treattracker.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.repositories.ProductRepository;
import com.skilldistillery.treattracker.repositories.UserRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Set<Product> index(String username) {
		return productRepo.findByUser_Username(username);
	}

	@Override
	public Product show(String username, int pid) {
		Optional<Product> productOpt = productRepo.findById(pid);
		if (productOpt.isPresent()) {
			Product product = productOpt.get();
			if (product.getUser().getUsername().equals(username)) {
				return product;
			}
		}
		return null;
	}

	@Override
	public Product create(String username, Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product update(String username, int tid, Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean destroy(String username, int tid) {
		// TODO Auto-generated method stub
		return false;
	}
}
//	GET    	/products    								get all products
//	GET    	/products/{productId}    					get product by ID
//	GET    	/products/{name or keyword}   				get product by word search
//	PUT    	/products/{productId}     					update product by id
//	PUT    	/products/{productId}     					deactivate product by id

//	GET    	/products/{productId}/reports    			find user updates about product
//	GET    	/products/{productId}/reports/{reportId}  	find specific report
//	POST   	/products/{productId}/reports  				create report
//	PUT    	/products/{productId}/reports/{reportId}   	update specific report
//	DELETE	/products/{productId}/reports/{reportId}   	delete specific report//

//	GET		/products/{productId}/ingredients   		find all ingredients
//	GET 	/products/{productId}/ingredients/{ingId}   find specific ingredient
//	POST	/products/{productId}/ingredients      		create ingredient
//	PUT 	/products/{productId}/ingredients/{ingId}   update specific ingredient-admin/ store only                                                               
//	DELETE 	/products/{productId}/ingredient/{ingId}  	delete specific ing-ad/only

//	GET 	/products/{productId}/comments      		find all comments
//	GET 	/products/{productId}/comments/{commentId}  find specific comment
//	POST	/products/{productId}/comments     			create comment
//	PUT 	/products/{productId}/comments/{commentId}  update comment
//	DELETE 	/products/{productId}/comments/{commentId}  delete comment