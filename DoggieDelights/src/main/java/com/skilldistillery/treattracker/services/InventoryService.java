package com.skilldistillery.treattracker.services;

import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;

public interface InventoryService {
	Inventory findInventoryById(int id);
	Inventory addNewProductByGivenStore(String username, Store store, Inventory inventory);

}
