package com.skilldistillery.treattracker.services;

import java.util.List;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Address;
import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;
import com.skilldistillery.treattracker.repositories.ProductRepository;
import com.skilldistillery.treattracker.repositories.StoreCommentRepository;
import com.skilldistillery.treattracker.repositories.StoreRepository;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreRepository StoreRepo;
	
	@Autowired
	private ProductRepository ProdRepo;
	
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
		List<Store> stores = StoreRepo.findByUsernameContains(keyword);
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
	public List<Product> findProductInventoryByStore(Store store, Inventory inventory) {
		// TODO Auto-generated method stub
		return null;
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
