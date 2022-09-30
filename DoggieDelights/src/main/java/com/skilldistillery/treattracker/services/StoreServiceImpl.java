package com.skilldistillery.treattracker.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Address;
import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.SortByRate;
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
	public List<Store> storesAscendingOrderByRate() {
		List<Store> sortedStore = new ArrayList<>();
		List<Store> unSortedStore = new ArrayList<>(storeRepo.findAll());
		List<Double> rateList = new ArrayList<>();
		List<Integer> rates = new ArrayList<>();//list of rates in each store
		double avgRate = 0;
		double sum = 0;
		int count = 0;
		//loop thru all of stores
		for (Store store : unSortedStore) {
			
			for (StoreComment comment : store.getComments()) {
				rates.add(comment.getRating());
				sum += comment.getRating();
			}
			avgRate = sum/rates.size();
			System.out.println(store + "rating " + rates + " avg " + avgRate);
			rateList.add(avgRate);
			rates = new ArrayList<>();
			sum = 0;
			avgRate = 0;
		}
		System.out.println(rateList);
		//By default the LinkedHashMap preserves the insertion order.
		//to maitain store with its corresponding rate
		Map<Store, Double> unsortedMap = new LinkedHashMap<>();
		for (int i = 0; i < unSortedStore.size(); i++) {
			unsortedMap.put(storeRepo.findAll().get(i), rateList.get(i));
		}
		System.out.println("before sorting " + unsortedMap);
		
		 // Now, getting all entries from map and convert it to a list using entrySet() method
        List<Map.Entry<Store, Double> > list
            = new ArrayList<Map.Entry<Store, Double> >(
                unsortedMap.entrySet());
     // Using collections class sort method and inside which we are using custom comparator to compare value of map
        Collections.sort(
            list,
            new Comparator<Map.Entry<Store, Double> >() {
                // Comparing two entries by value
                public int compare(
                    Map.Entry<Store, Double> entry1,
                    Map.Entry<Store, Double> entry2)  {
 
                    // Subtracting the entries
                    return (int) (entry1.getValue()- entry2.getValue());
                }
            });
        
     // Iterating over the sorted map
        // using the for each method
        for (Map.Entry<Store, Double> l : list) {
 
            // Printing the sorted map
            // using getKey()  and getValue() methods
            System.out.println("Key ->"
                               + " " + l.getKey()
                               + ": Value ->"
                               + l.getValue());
            sortedStore.add(l.getKey());
        }
        System.out.println("after sorting: "+ sortedStore);

		return sortedStore;
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
	public Set<Store> findStoresByProductKeywordSearch(String keyword) {
		String kw = "%" + keyword + "%";
		Set<Store> stores = storeRepo
				.findByInventories_Product_NameIgnoreCaseLikeOrInventories_Product_BrandIgnoreCaseLikeOrInventories_Product_DescriptionIgnoreCaseLike(
						kw, kw, kw);
		Set<Store> storeByRep = storeRepo
				.findByProductReports_Product_NameIgnoreCaseLikeOrProductReports_Product_BrandIgnoreCaseLikeOrProductReports_Product_DescriptionIgnoreCaseLike(
						kw, kw, kw);
		System.out.println("stores inventory array size: " + stores.size());
		System.out.println("stores PR array size: " + storeByRep.size());
		stores.addAll(storeByRep);
		System.out.println("combined array size: " + stores.size());
		return stores;
	}

	@Override
	public Store createStore(String username, Store newStore) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			newStore.setUser(user);
			user.addStoreToUser(newStore);
			Address address = newStore.getAddress();
			addressRepo.saveAndFlush(address);
			newStore.setEnabled(true);
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
			if (store.isEnabled()) {
				existingStore.setEnabled(store.isEnabled());
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
	public Inventory findInventoryByStoreAndProduct(String username, Store store, Product prod) {
		Inventory item = inventoryRepo.findByStoreIdAndProductId(store.getId(), prod.getId());
		return item;
	}

	@Override
	public Inventory updateProductInventoryByStore(String username, Store store, Product product, Inventory inventory) {
		User user = userRepo.findByUsername(username);
		Inventory existingInventory = inventoryRepo.findByStoreIdAndProductId(store.getId(), product.getId());
		if (user != null) {
			if (inventory.getQuantity() != null) {
				existingInventory.setQuantity(inventory.getQuantity());
				System.out.println("inside store service " + inventory.getQuantity());

				System.out.println(inventoryRepo.saveAndFlush(existingInventory));
			}
		}
		return existingInventory;
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

	// return all of comments of store
	@Override
	public List<StoreComment> findStoreComments(String username, Store store) {
		return store.getComments();
	}

	// return all of comments of store
	@Override
	public StoreComment findStoreCommentById(String username, Store store, int storeCommentId) {
		User userLoggined = userRepo.findByUsername(username);
		StoreComment storeCom = null;
		if (userLoggined != null) {
			storeCom = storeCommentRepo.findByIdAndStoreId(storeCommentId, store.getId());
		}
		return storeCom;
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
	public boolean deleteCommentStore(String username, Store store, StoreComment comment) {
		boolean isDeleted = false;
		User userLoggedIn = userRepo.findByUsername(username);
		if (userLoggedIn != null) {
			StoreComment commentToDelete = storeCommentRepo.findByIdAndStoreId(comment.getId(), store.getId());
			commentToDelete.removeComments();
			commentToDelete.setParentStoreComment(null);
			storeCommentRepo.delete(commentToDelete);
			isDeleted = true;
		}
		return isDeleted;
	}

}
