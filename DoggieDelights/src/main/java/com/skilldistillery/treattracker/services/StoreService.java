package com.skilldistillery.treattracker.services;

import java.util.List;
import java.util.Map;

import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;
import com.skilldistillery.treattracker.entities.StoreComment;
import com.skilldistillery.treattracker.entities.User;

public interface StoreService {
	List<Store> findAllStores();
	Store findStorebyId(int storeId, String username);
	List<Store> findStoresByKeyword(String keyword);
	Store createStore(String username, Store newStore);
	Store updateStore(Store store, int storeId, String username);
	boolean deleteStore( String username, int storeId);
	 Map<Product, Integer>findProductInventoryByStore(String username,Store store);
	List<Inventory> updateProductInventoryByStore(String username, Store store, Product prod, int updatedQuantity);
	boolean deactivateProductInventoryByStore(String username, Store store, Product prod, Inventory inventory);
	List<StoreComment> findStoreComments(String username, Store store);
	StoreComment postCommentToStore(String username,Store store, StoreComment comment);
	StoreComment postCommentToParentCommentToStore(String username,Store store, int parentStoreComment, StoreComment comment);
	StoreComment updateCommentStore(Store store, StoreComment comment);
	boolean deleteCommentStore(Store store, StoreComment comment);
	
	
	
	

}
