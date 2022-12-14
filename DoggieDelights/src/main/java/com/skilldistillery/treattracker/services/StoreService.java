package com.skilldistillery.treattracker.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;
import com.skilldistillery.treattracker.entities.StoreComment;

public interface StoreService {
	List<Store> findAllStores();
	List<Store> storesAscendingOrderByRate();
	Store findStorebyId(int storeId, String username);
	
	List<Store> findStoresByKeyword(String keyword);
	Store createStore(String username, Store newStore);
	Store updateStore(Store store, int storeId, String username);
	boolean deleteStore( String username, int storeId);
	 Map<Product, Integer>findProductInventoryByStore(String username,Store store);
	 Set<Product> findProductsByStore(String username, Store store);
	 Inventory findInventoryByStoreAndProduct(String username, Store store, Product prod);
	Inventory updateProductInventoryByStore(String username, Store store, Product prod, Inventory inventory);
	boolean deactivateProductInventoryByStore(String username, Store store, Product prod, Inventory inventory);
	List<StoreComment> findStoreComments(String username, Store store);
	StoreComment findStoreCommentById(String username, Store store, int storeComment);
	StoreComment postCommentToStore(String username,Store store, StoreComment comment);
	StoreComment postCommentToParentCommentToStore(String username,Store store, int parentStoreComment, StoreComment comment);
	StoreComment updateCommentStore(Store store, StoreComment comment);
	boolean deleteCommentStore(String username, Store store, StoreComment comment);
	Set<Store> findStoresByProductKeywordSearch(String keyword);
	
//	Product createProductByGivenStore(String username, Store store, Product product);
	
	
	
	

}
