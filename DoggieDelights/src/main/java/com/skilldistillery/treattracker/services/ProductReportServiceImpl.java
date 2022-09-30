package com.skilldistillery.treattracker.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.ProductReport;
import com.skilldistillery.treattracker.entities.Store;
import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.repositories.ProductReportRepository;
import com.skilldistillery.treattracker.repositories.ProductRepository;
import com.skilldistillery.treattracker.repositories.StoreRepository;
import com.skilldistillery.treattracker.repositories.UserRepository;

@Service
public class ProductReportServiceImpl implements ProductReportService {

	@Autowired
	private ProductReportRepository prodReportRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private StoreRepository storeRepo;
	
	@Autowired
	private ProductRepository prodRepo;

	@Override
	public List<ProductReport> index(String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return prodReportRepo.findAll();
		}
		return null;
	}

//	@Override
//	public ProductReport show(String username, int pid) {
//		User user = userRepo.findByUsername(username);
//		Optional<ProductReport> prodOpt = prodRepo.findById(pid);
//		if (prodOpt.isPresent()) {
//			ProductReport product = prodOpt.get();
//			if (user != null) {
//				return product;
//			}
//		}
//		return null;
//	}

	@Override
	public ProductReport create(String username, ProductReport report, int pid, int sid) {
		User user = userRepo.findByUsername(username);
		Optional<Product> prodOpt = prodRepo.findById(pid);
		Store store = storeRepo.findById(sid);
		
		if (user != null && prodOpt.isPresent() && store != null) {
//			report.setCreatedOn(LocalDateTime.now());
			report.setUser(user);
			report.setProduct(prodOpt.get());
			report.setStore(store);
			
			return prodReportRepo.saveAndFlush(report);
		}
		return null;
	}

	@Override
	public ProductReport update(String username, int rid, ProductReport report) {
		User user = userRepo.findByUsername(username);
		Optional<ProductReport> reportOpt = prodReportRepo.findById(rid);
		if (reportOpt.isPresent()) {
			ProductReport reportUpdate = reportOpt.get();
			if (user != null) {
				reportUpdate.setUpdatedOn(LocalDateTime.now());
				reportUpdate.setUserQuantity(report.getUserQuantity());
				reportUpdate.setPrice(report.getPrice());
				reportUpdate.setIsInStock(report.getIsInStock());
				reportUpdate.setRemark(report.getRemark());

				prodReportRepo.save(reportUpdate);
				return reportUpdate;
			}
		}
		return null;
	}

	@Override
	public boolean destroy(String username, int rid) {
		User user = userRepo.findByUsername(username);
		Optional<ProductReport> reportOpt = prodReportRepo.findById(rid);
		if (reportOpt.isPresent()) {
			if (user != null) {
				try {
					prodReportRepo.deleteById(rid);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}