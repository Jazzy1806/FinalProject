package com.skilldistillery.treattracker.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class StoreController {


//	GET   /stores     get all stores
//
//	GET /stores/{storeId}    get store by id
//
//	GET  /stores/{keyword/name}   get store by word search
//
//	POST /stores    create new store
//
//	PUT /stores/{storeId}    update store
//
//	PUT /stores/{storeId}    update store for deactivation
//
//	DELETE /stores/{storeId}  delete store
//
//
//
//	PUT  /stores/[storeId}/addresses/{addressId}  update address for store
//
//	GET /stores/{storeId}/inventory/{inventoryId}    get specific product list
//
//	PUT /stores/{storeId}/inventory/{inventoryId}    update specific product list
//
//	PUT /stores/{storeId}/inventory/{inventoryId}    deactivate specific product list
//
//
//
//	GET /stores/{storeId}/comments      get all comments
//
//	POST /stores/{storeId}/comments      create new comment
//
//	PUT /stores/{storeId}/comments/{commentId}      update comment
//
//	DELETE /stores/{storeId}/comments/{commentId}      delete comment

}
