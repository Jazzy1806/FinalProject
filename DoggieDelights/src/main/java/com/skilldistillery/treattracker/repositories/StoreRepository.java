package com.skilldistillery.treattracker.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
	Store findById(int storeId);
	List<Store> findByNameContains(String keyword);
	
	Set<Store> findByInventories_Product_NameIgnoreCaseLikeOrInventories_Product_BrandIgnoreCaseLikeOrInventories_Product_DescriptionIgnoreCaseLike(String prodName, String brand, String desc);

	Set<Store> findByProductReports_Product_NameIgnoreCaseLikeOrProductReports_Product_BrandIgnoreCaseLikeOrProductReports_Product_DescriptionIgnoreCaseLike(String prodName, String brand, String desc);
}
