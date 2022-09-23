package com.skilldistillery.treattracker.services;

import java.util.List;
import java.util.Map;

import javax.xml.stream.events.Comment;

import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;

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
	List<Comment> findStoreComments(Store store);
	Comment postCommentToStore(Store store, Comment comment);
	Comment updateCommentStore(Store store, Comment comment);
	boolean deleteCommentStore(Store store, Comment comment);
	
	
	
	

}
