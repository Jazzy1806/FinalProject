package com.skilldistillery.treattracker.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Address;
import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;
import com.skilldistillery.treattracker.repositories.InventoryRepository;
import com.skilldistillery.treattracker.repositories.StoreCommentRepository;
import com.skilldistillery.treattracker.repositories.StoreRepository;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreRepository StoreRepo;
	
	@Autowired
	private InventoryRepository inventoryRepo;
	
	@Autowired
	private StoreCommentRepository storeCommentRepo;

	@Override
	public List<Store> findAllStores() {
		List<Store> stores = StoreRepo.findAll();
		return stores;
	}

	@Override
	public Store findStorebyId(int storeId) {
		Store store = StoreRepo.findById(storeId);
		return store;
	}

	@Override
	public List<Store> findStoresByKeyword(String keyword) {
		List<Store> stores = StoreRepo.findByNameContains(keyword);
		return stores;
	}

	@Override
	public Store createStore(Store newStore) {
		//Store add
		return null;
	}

	@Override
	public Store updateStore(Store store) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteStore(Store store) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Address updateStoreAddress(Store store) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Product> findProductInventoryByStore(Store store) {
		Set<Product> products = new HashSet<>();
		List<Inventory> inventories = inventoryRepo.findByStore(store);
		for (Inventory item : inventories ) {
			products.add(item.getProduct());
		}
		return products;
	}

	@Override
	public Product updateProductInventoryByStore(Store store, Inventory inventory, Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deactivateProductListInventoryByStore(Store store, Inventory inventory, Product product) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<Comment> findStoreComments(Store store) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment postCommentToStore(Store store, Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment updateCommentStore(Store store, Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCommentStore(Store store, Comment comment) {
		// TODO Auto-generated method stub
		return false;
	}

}
