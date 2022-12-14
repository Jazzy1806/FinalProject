package com.skilldistillery.treattracker.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.treattracker.entities.Inventory;
import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.Store;
import com.skilldistillery.treattracker.entities.StoreComment;
import com.skilldistillery.treattracker.services.InventoryService;
import com.skilldistillery.treattracker.services.ProductService;
import com.skilldistillery.treattracker.services.StoreService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class StoreController {

	@Autowired
	private StoreService storeServ;
	
	@Autowired
	private ProductService prodServ;
	@Autowired
	private InventoryService inventoryServ;

	

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
	
	@GetMapping("stores/search/products/{keyword}")
	public Set<Store> getStoresByProductKeywordSearch(@PathVariable String keyword, HttpServletResponse res) {
		Set<Store> stores = null;
		try {
			stores = storeServ.findStoresByProductKeywordSearch(keyword);
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
	public Store updateStore(@PathVariable("id") Integer id, @RequestBody Store store, HttpServletResponse res, Principal principal) {
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
	public void deleteStore(@PathVariable Integer id, HttpServletResponse res, Principal principal) {
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
//	GET /stores/{storeId}/products   get  product list by store
	@RequestMapping("stores/{storeId}/products")
	public  Set<Product> getProductsByStore(@PathVariable int storeId,Principal principal, HttpServletResponse res) {
		Set<Product> products = null;
		Store store = storeServ.findStorebyId(storeId, principal.getName());
		try {
			products = storeServ.findProductsByStore( principal.getName(), store );
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return products;
		
	}
	//retrieve unique inventory based on store and product combination
	@RequestMapping("stores/{storeId}/product/{prodId}/inventory")
	public Inventory getInventoryByProductAndStore(@PathVariable int storeId, @PathVariable int prodId, Principal principal, HttpServletResponse res) {
		Product productToUpdateInventory = prodServ.findById(principal.getName(), prodId);
		Store store = storeServ.findStorebyId(storeId, principal.getName());
		Inventory inventoryFound = null;
		try {
		inventoryFound = storeServ.findInventoryByStoreAndProduct( principal.getName(), store, productToUpdateInventory);
			System.out.println();
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return inventoryFound;

	}
	
	
	//	PUT /stores/{storeId}/inventory/{inventoryId}    update specific product list
//	CREATE /stores/{storeId}/inventory/{inventoryId}    add specific product to store => add more inventory to an existing product
	@PutMapping("stores/{storeId}/product/{prodId}/inventory")
	public Inventory addProductInventoryByStore(@PathVariable int storeId, @PathVariable int prodId,  @RequestBody Inventory inventory, Principal principal, HttpServletResponse res) {
		Product productToUpdateInventory = prodServ.findById(principal.getName(), prodId);
		Store store = storeServ.findStorebyId(storeId, principal.getName());
		try {
		inventory = storeServ.updateProductInventoryByStore( principal.getName(), store, productToUpdateInventory, inventory);
			System.out.println();
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return inventory;

	}
//	PUT /stores/{storeId}/inventory/{inventoryId}    deactivate specific product list
	@PutMapping("stores/{storeId}/product/{prodId}/inventory/{inventoryId}")
	public void deactivateProductInventoryByStore(@PathVariable int storeId, @PathVariable int prodId,@PathVariable int inventoryId, Principal principal, HttpServletResponse res) {
	
		Product prod = prodServ.findById(principal.getName(), prodId);
		Inventory inventory = inventoryServ.findInventoryById(inventoryId);
		Store store = storeServ.findStorebyId(storeId, principal.getName());
		try {
			if (storeServ.deactivateProductInventoryByStore(principal.getName(), store, prod,inventory )) {
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
	@RequestMapping("/stores/{storeId}/comments")
	public List<StoreComment> getAllStoreComments(@PathVariable int storeId,Principal principal, HttpServletResponse res ) {
		Store store = storeServ.findStorebyId(storeId, principal.getName());
		if (store == null) {
			res.setStatus(404);
		}
		return store.getComments();
	}
//	POST /stores/{storeId}/comments      create new comment
	@PostMapping("/stores/{storeId}/comments/comment")
	public StoreComment createStoreComment(@PathVariable int storeId, @RequestBody StoreComment comment, Principal principal, HttpServletResponse res ) {
		Store store = storeServ.findStorebyId(storeId, principal.getName());
		
		StoreComment storeComment = storeServ.postCommentToStore( principal.getName(), store, comment);
		System.out.println("Inside post store comment controller");
		if (storeComment == null) {
			res.setStatus(404);
		}
		return storeComment;
	}
//	POST /stores/{storeId}/comments      create  comment on parent comment
	@PostMapping("/stores/{storeId}/comments/{parentCommentId}/comment")
	public StoreComment createStoreCommentToParentComent(@PathVariable int storeId, @PathVariable int parentCommentId, @RequestBody StoreComment comment, Principal principal, HttpServletResponse res ) {
		Store store = storeServ.findStorebyId(storeId, principal.getName());
		
		StoreComment childComment = storeServ.postCommentToParentCommentToStore( principal.getName(), store,parentCommentId, comment);
		System.out.println("Inside post  store child comment controller");
		if (childComment == null) {
			res.setStatus(404);
		}
		return childComment;
	}
//	PUT /stores/{storeId}/comments/{commentId}      update comment
//
//	DELETE /stores/{storeId}/comments/{commentId}      delete comment
	@DeleteMapping("/stores/{storeId}/comments/{commentId}")
	public void deleteStoreComment(@PathVariable int storeId, @PathVariable int commentId, Principal principal, HttpServletResponse res ) {
		Store store = storeServ.findStorebyId(storeId, principal.getName());
		StoreComment storeComment = storeServ.findStoreCommentById(principal.getName(), store, commentId);
		try {
			if (storeServ.deleteCommentStore(principal.getName(), store, storeComment)) {
				res.setStatus(204);
			}else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	//add newProduct to a given store
	@PostMapping("stores/{storeId}/inventory")
	public Inventory addProductInventoryToGivenStore(@PathVariable int storeId,  @RequestBody Inventory inventory, Principal principal, HttpServletResponse res) {
		
		Store store = storeServ.findStorebyId(storeId, principal.getName());
		try {
		inventory = inventoryServ.addNewProductByGivenStore( principal.getName(), store, inventory);
			System.out.println(inventory);
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return inventory;

	}
}
