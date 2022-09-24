package com.skilldistillery.treattracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
    List<Product> findByNameIgnoreCaseLikeOrBrandIgnoreCaseLikeOrDescriptionIgnoreCaseLikeOrderByNameAsc(String keyword, String keyword2, String keyword3);

}
