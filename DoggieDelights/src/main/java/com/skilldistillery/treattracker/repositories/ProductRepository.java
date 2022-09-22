package com.skilldistillery.treattracker.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
//	Set<Product> findByUser_Username(String username);
	
	
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
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;

public interface ProductRepository extends JpaRepository<Product,Integer> {
	List<Product> findByInventoryAndStore(Inventory inventory, Store store);

}
