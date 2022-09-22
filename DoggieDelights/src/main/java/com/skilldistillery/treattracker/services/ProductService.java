package com.skilldistillery.treattracker.services;

import java.util.List;

import com.skilldistillery.treattracker.entities.Product;

public interface ProductService {

	public List<Product> index(String username);

	public Product show(String username, int pid);

	public Product create(String username, Product product);

	public Product update(String username, int tid, Product product);

	public boolean destroy(String username, int tid);
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
