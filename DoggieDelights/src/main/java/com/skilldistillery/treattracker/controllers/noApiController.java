package com.skilldistillery.treattracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;
import com.skilldistillery.treattracker.services.ProductService;
import com.skilldistillery.treattracker.services.StoreService;

@RestController
@RequestMapping("home")
@CrossOrigin({ "*", "http://localhost:4200/" })
public class noApiController {
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ProductService prodService;
	
	@GetMapping("products")
	public List<Product> productList(HttpServletRequest req, HttpServletResponse res) {
		return prodService.index();
	}
	@GetMapping("stores")
	public List<Store> storeList(HttpServletRequest req, HttpServletResponse res) {
		return storeService.storesAscendingOrderByRate();
//		return storeService.findAllStores();
	}

}
