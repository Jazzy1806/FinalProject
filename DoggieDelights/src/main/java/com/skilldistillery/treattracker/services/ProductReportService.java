package com.skilldistillery.treattracker.services;

import java.util.List;

import com.skilldistillery.treattracker.entities.ProductReport;

public interface ProductReportService {

	public List<ProductReport> index(String username);

	// public ProductReport show(String username, int pid);

	public ProductReport create(String username, ProductReport report, int pid, int sid);

	public ProductReport update(String username, int rid, ProductReport report);

	public boolean destroy(String username, int rid);
}