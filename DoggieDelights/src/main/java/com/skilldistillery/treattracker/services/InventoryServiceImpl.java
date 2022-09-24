package com.skilldistillery.treattracker.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.repositories.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepo;
	
	@Override
	public Inventory findInventoryById(int id) {
		Inventory inventory = inventoryRepo.findById(id);
		
		return inventory;
	}

}
