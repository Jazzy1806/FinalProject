package com.skilldistillery.treattracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.ProductReport;

public interface ProductReportRepository extends JpaRepository<ProductReport, Integer> {

	public List<ProductReport> findByProductId(int pid);
	
	public ProductReport findByIdAndProductId(int rid, int pid);
	

}
