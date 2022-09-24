package com.skilldistillery.treattracker.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Address;
import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;
import com.skilldistillery.treattracker.entities.StoreComment;
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
	public boolean deleteStore(String username, int storeId) {
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
	public Map<Product, Integer> findProductInventoryByStore(String username, Store store) {
		Map<Product, Integer> productInventory = new HashMap<>();
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Set<Product> products = new HashSet<>();
			List<Inventory> inventories = inventoryRepo.findByStore(store);
			for (Inventory item : inventories) {
				products.add(item.getProduct());
			}
			for (Product prod : products) {
				productInventory.put(prod, prod.getInventoryItems().size());
			}
		}
		return productInventory;
	}
	
	@Override
	public Set<Product> findProductsByStore(String username, Store store) {
		User user = userRepo.findByUsername(username);
		Set<Product> products = new HashSet<>();
		if (user != null) {
			
			List<Inventory> inventories = inventoryRepo.findByStore(store);
			for (Inventory item : inventories) {
				products.add(item.getProduct());
			}
		}
		
		return products;
	}

	public Double getProductPrice(Product product) {
		Double price = 0.0;
		for (Inventory item : product.getInventoryItems()) {
			price = item.getPrice();
		}
		return price;
	}

	@Override
	public List<Inventory> updateProductInventoryByStore(String username, Store store, Product product,
			int updatedQuantity) {
		User user = userRepo.findByUsername(username);
		Double price = getProductPrice(product);
		if (user != null) {
			for (int i = 0; i < updatedQuantity; i++) {
				Inventory inventory = new Inventory();
				inventory.setStore(store);
				inventory.setProduct(product);
				inventory.setPrice(price);
				inventory.setEnabled(true);
				inventoryRepo.saveAndFlush(inventory);
			}

		}
		System.out.println(product.getInventoryItems());
		return product.getInventoryItems();
	}

	@Override
	public boolean deactivateProductInventoryByStore(String username, Store store, Product prod, Inventory inventory) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			if (prod.getInventoryItems().contains(inventory)) {
				inventory.setEnabled(false);
				inventoryRepo.saveAndFlush(inventory);
				System.out.println("inside if statement for deactive");
				return true;

			}
		}
		return false;
	}
	//return all of comments of store
	@Override
	public List<StoreComment> findStoreComments(String username, Store store) {
		return store.getComments();
	}

	@Override
	public StoreComment postCommentToStore(String username, Store store, StoreComment comment) {
		User userLoggined = userRepo.findByUsername(username);
		if (userLoggined != null) {
			comment.setStore(store);
			System.out.println("Inside post store comment service impl");
			storeCommentRepo.saveAndFlush(comment);
		}
		return comment;
	}

	@Override
	public StoreComment postCommentToParentCommentToStore(String username, Store store, int parentStoreComment,
			StoreComment comment) {
		User userLoggined = userRepo.findByUsername(username);
		Optional<StoreComment> parentStoreComOp = storeCommentRepo.findById(parentStoreComment);
		if (parentStoreComOp.isPresent()) {
			if (userLoggined != null) {
				StoreComment parentStoreCom = parentStoreComOp.get();
				comment.setParentStoreComment(parentStoreCom);
				comment.setStore(store);
				System.out.println("Inside post comment to parent Store comment service impl");
				parentStoreCom.getReplyStoreComments().add(comment);
				storeCommentRepo.saveAndFlush(comment);
			}
		}
		return comment;
	}

	@Override
	public StoreComment updateCommentStore(Store store, StoreComment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCommentStore(Store store, StoreComment comment) {
		// TODO Auto-generated method stub
		return false;
	}

}
