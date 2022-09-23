package com.skilldistillery.treattracker.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Address;
import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;
import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.repositories.AddressRepository;
import com.skilldistillery.treattracker.repositories.InventoryRepository;
import com.skilldistillery.treattracker.repositories.StoreCommentRepository;
import com.skilldistillery.treattracker.repositories.StoreRepository;
import com.skilldistillery.treattracker.repositories.UserRepository;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private StoreRepository storeRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private InventoryRepository inventoryRepo;

	@Autowired
	private StoreCommentRepository storeCommentRepo;

	@Override
	public List<Store> findAllStores() {
		return storeRepo.findAll();
	}

	@Override
	public Store findStorebyId(int storeId, String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return storeRepo.findById(storeId);
		}
		return null;
	}

	@Override
	public List<Store> findStoresByKeyword(String keyword) {
		List<Store> stores = storeRepo.findByNameContains(keyword);
		System.out.println(stores.size());
		return stores;
	}

	@Override
	public Store createStore(String username, Store newStore) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Address address = newStore.getAddress();
			addressRepo.saveAndFlush(address);
			System.out.println("inside createStore serv" + newStore);
			return storeRepo.saveAndFlush(newStore);
			
		}
		return null;
	}

	@Override
	public Store updateStore(Store store, int storeId, String username) {
		System.out.println(store.getName());
		Store existingStore = storeRepo.findById(storeId);
		User user = userRepo.findByUsername(username);
		if (user != null) {
			if (store.getAddress() != null) {
				existingStore.setAddress(store.getAddress());
			}
			if (store.getChain() != null) {
				existingStore.setChain(store.getChain());
			}
			if (store.getDescription() != null) {
				existingStore.setDescription(store.getDescription());
			}
			System.out.println("updated name " + store.getName());
			if (store.getName() != null) {
				existingStore.setName(store.getName());
			}
			if (store.getWebsiteUrl() != null) {
				existingStore.setWebsiteUrl(store.getWebsiteUrl());
			}
			System.out.println("inside stockservice impl" + existingStore);
			return storeRepo.saveAndFlush(existingStore);
		}
		return null;
	}

	@Override
	public boolean deleteStore( String username, int storeId) {
		boolean isDeleted = false;
		Store existingStore = storeRepo.findById(storeId);
		User user = userRepo.findByUsername(username);
		if (user != null) {
			try {
				existingStore.setAddress(null);
				existingStore.setChain(null);
				storeRepo.delete(existingStore);
			isDeleted = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return isDeleted;
	}


	@Override
	public Set<Product> findProductInventoryByStore(Store store) {
		Set<Product> products = new HashSet<>();
		List<Inventory> inventories = inventoryRepo.findByStore(store);
		for (Inventory item : inventories) {
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
