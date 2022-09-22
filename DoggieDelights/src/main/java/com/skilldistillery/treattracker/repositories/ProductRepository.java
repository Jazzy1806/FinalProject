package com.skilldistillery.treattracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {


}
