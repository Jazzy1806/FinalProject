package com.skilldistillery.treattracker.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.ProductReport;
import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.repositories.ProductReportRepository;
import com.skilldistillery.treattracker.repositories.UserRepository;

@Service
public class ProductReportServiceImpl implements ProductReportService {

	@Autowired
	private ProductReportRepository prodReportRepo;

	@Autowired
	private UserRepository userRepo;

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
	public ProductReport create(String username, ProductReport product) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			product.setCreatedOn(LocalDateTime.now());
			return prodReportRepo.saveAndFlush(product);
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