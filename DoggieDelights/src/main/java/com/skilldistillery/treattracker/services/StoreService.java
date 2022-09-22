package com.skilldistillery.treattracker.services;

import java.util.List;

import javax.xml.stream.events.Comment;

import com.skilldistillery.treattracker.entities.Address;
import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;

public interface StoreService {
	List<Store> findAllStores();
	Store findStorebyId(int storeId);
	List<Store> findStoresByKeyword(String keyword);
	Store createStore(Store newStore);
	Store updateStore(Store store);
	boolean deleteStore(Store store);
	Address updateStoreAddress(Store store);
	List<Product> findProductInventoryByStore(Store store, Inventory inventory);
	Product updateProductInventoryByStore(Store store, Inventory inventory, Product product);
	boolean deactivateProductListInventoryByStore(Store store, Inventory inventory, Product product);
	List<Comment> findStoreComments(Store store);
	Comment postCommentToStore(Store store, Comment comment);
	Comment updateCommentStore(Store store, Comment comment);
	boolean deleteCommentStore(Store store, Comment comment);
	
	
	
	

}