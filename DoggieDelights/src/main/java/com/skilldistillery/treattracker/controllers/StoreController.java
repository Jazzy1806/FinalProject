package com.skilldistillery.treattracker.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;
import com.skilldistillery.treattracker.services.ProductService;
import com.skilldistillery.treattracker.services.StoreService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class StoreController {

	@Autowired
	private StoreService storeServ;
	
	@Autowired
	private ProductService prodServ;


//	GET   /stores     get all stores
	@RequestMapping("stores")
	public List<Store> getAllStores(HttpServletResponse res) {
		List<Store> stores = null;
		try {
			stores = storeServ.findAllStores();
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return stores;

	}

	// GET /stores/{storeId} get store by id
	@RequestMapping("stores/{storeId}")
	public Store getStoreById(@PathVariable int storeId,Principal principal, HttpServletResponse res) {
		Store store = null;
		try {
			store = storeServ.findStorebyId(storeId, principal.getName());
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return store;

	}

	// GET /stores/{keyword/name} get store by word search
	@RequestMapping("stores/search/{keyword}")
	public List<Store> getStoreByKeyword(@PathVariable String keyword, HttpServletResponse res) {
		List<Store> stores = null;
		try {
			stores = storeServ.findStoresByKeyword(keyword);
			System.out.println("inside method controller " + stores);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return stores;
	}

//	POST /stores    create new store
	@PostMapping("stores")
	public Store createStore(@RequestBody Store store, Principal principal, HttpServletRequest req, HttpServletResponse res ) {
		Store storeAdded;
		try {
			storeAdded = storeServ.createStore(principal.getName(), store);
			System.out.println("Added " + storeAdded);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(store.getId());
			res.setHeader("Store", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			storeAdded = null;
		}
		
		return storeAdded;
	}
//	PUT /stores/{storeId}    update store
	@PutMapping("stores/{id}")
	public Store updateStock(@PathVariable("id") Integer id, @RequestBody Store store, HttpServletResponse res, Principal principal) {
		Store storeToUpdate;
		try {
			 storeToUpdate = storeServ.updateStore( store, id, principal.getName());
			if ( storeToUpdate == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			storeToUpdate  = null;
		}
		
		return storeToUpdate;
	}
//	PUT /stores/{storeId}    update store for deactivation
//
//	DELETE /stores/{storeId}  delete store
	@DeleteMapping("stores/{id}")
	public void deleteComment(@PathVariable Integer id, HttpServletResponse res, Principal principal) {
		try {
			if (storeServ.deleteStore(principal.getName(), id)) {
				res.setStatus(204);
			}else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
	}
//	PUT  /stores/[storeId}/addresses/{addressId}  update address for store
//
//	GET /stores/{storeId}/inventory/{inventoryId}    get specific product list
	@RequestMapping("stores/{storeId}/inventory")
	public Map<Product, Integer> getProductInventoryByStore(@PathVariable int storeId,Principal principal, HttpServletResponse res) {
		Map<Product, Integer> productInventoryByStore = null;
		Store store = storeServ.findStorebyId(storeId, principal.getName());
		try {
			productInventoryByStore = storeServ.findProductInventoryByStore( principal.getName(), store );
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return productInventoryByStore;

	}
//	PUT /stores/{storeId}/inventory/{inventoryId}    update specific product list
//	CREATE /stores/{storeId}/inventory/{inventoryId}    add specific product to store => add more inventory to an existing product
	@PostMapping("stores/{storeId}/product/{prodId}/inventory/{quantity}")
	public List<Inventory> addProductInventoryByStore(@PathVariable int storeId, @PathVariable int prodId,@PathVariable int quantity, Principal principal, HttpServletResponse res) {
		List<Inventory> updatedInventories = null;
		Product productToUpdateInventory = prodServ.findById(principal.getName(), prodId);
		Store store = storeServ.findStorebyId(storeId, principal.getName());
		try {
			updatedInventories = storeServ.updateProductInventoryByStore( principal.getName(), store, productToUpdateInventory, quantity);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return updatedInventories;

	}
//	PUT /stores/{storeId}/inventory/{inventoryId}    deactivate specific product list
	@PutMapping("stores/{storeId}/product/{prodId}/inventory/{inventoryId}")
	public void deactivateProductInventoryByStore(@PathVariable int storeId, @PathVariable int prodId,@PathVariable int inventoryId, Principal principal, HttpServletResponse res) {
		
		Product prod = prodServ.findById(principal.getName(), prodId);
		Store store = storeServ.findStorebyId(storeId, principal.getName());
		try {
			if (prodServ.deactivateProductInventoryByStore(principal.getName(), store, prod, )) {
				res.setStatus(204);
			}else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}

	}
	//	GET /stores/{storeId}/comments      get all comments
//
//	POST /stores/{storeId}/comments      create new comment
//
//	PUT /stores/{storeId}/comments/{commentId}      update comment
//
//	DELETE /stores/{storeId}/comments/{commentId}      delete comment

}
