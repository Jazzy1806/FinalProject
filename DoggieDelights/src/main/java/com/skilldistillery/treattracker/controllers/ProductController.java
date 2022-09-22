package com.skilldistillery.treattracker.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.services.ProductService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class ProductController {

	@Autowired
	private ProductService prodService;

	@GetMapping("products")
	public List<Product> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return prodService.index(principal.getName());
	}

	@GetMapping("products/{pid}")
	public Product show(@PathVariable int pid, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		Product product = prodService.show(principal.getName(), pid);
		if (product == null) {
			res.setStatus(404);
		}
		return product;
	}
	
	@PostMapping("products")
	public Product create(@RequestBody Product product, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		Product created = null;
		try {
			created = prodService.create(principal.getName(), product);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(product.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return created;
	}
	
	@PutMapping("products/{pid}")
	public Product update(@PathVariable int pid, @RequestBody Product prodUpdate, HttpServletRequest req,
			HttpServletResponse res,
			Principal principal) {
		Product updated = null;
		try {
			updated = prodService.update(principal.getName(), pid, prodUpdate);
			if (updated == null) {
				res.setStatus(404);
			}
			res.setHeader("Location", req.getRequestURL().toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updated;
	}

	@DeleteMapping("products/{pid}")
	public Boolean destroy(@PathVariable int pid, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		Boolean deleted = prodService.destroy(principal.getName(), pid);
		if (deleted) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
		return deleted;
	}
	
	
	
	
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
}
