package com.skilldistillery.treattracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
	Store findById(int storeId);
	List<Store> findByNameContains(String keyword);
	

}
