package com.skilldistillery.treattracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;

public interface ProductRepository extends JpaRepository<Product,Integer> {
	List<Product> findByInventoryAndStore(Inventory inventory, Store store);

}
