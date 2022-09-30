package com.skilldistillery.treattracker.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;
import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.repositories.InventoryRepository;
import com.skilldistillery.treattracker.repositories.ProductRepository;
import com.skilldistillery.treattracker.repositories.UserRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
	private ProductRepository prodRepo;
	
	@Autowired
	private ProductService prodServ;
	
	@Override
	public Inventory findInventoryById(int id) {
		Inventory inventory = inventoryRepo.findById(id);
		
		return inventory;
	}

	@Override
	public Inventory addNewProductByGivenStore(String username, Store store, Inventory inventory) {
		Inventory newInventory = null;
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Product newProd = inventory.getProduct();
			Product newProd2 = prodServ.create(username, newProd);
			System.out.println("new Product" + newProd);
			System.out.println("new Product2" + newProd2);
			//prodRepo.saveAndFlush(newProd);
			inventory.setProduct(newProd2);
			inventory.setStore(store);
			store.addInventory(newInventory);
			newInventory = inventoryRepo.saveAndFlush(inventory);
			System.out.println("new Inventory " + newInventory);
			return newInventory;
		}
		return null;
	}

}
