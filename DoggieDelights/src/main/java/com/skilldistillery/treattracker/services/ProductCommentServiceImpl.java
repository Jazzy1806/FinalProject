package com.skilldistillery.treattracker.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.Product;
import com.skilldistillery.treattracker.entities.ProductComment;
import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.repositories.ProductCommentRepository;
import com.skilldistillery.treattracker.repositories.ProductRepository;
import com.skilldistillery.treattracker.repositories.UserRepository;

@Service
public class ProductCommentServiceImpl implements ProductCommentService {

	@Autowired
	private ProductCommentRepository commentRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProductRepository prodRepo;

	@Override
	public List<ProductComment> index(String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return commentRepo.findAll();
		}
		return null;
	}

	@Override
	public ProductComment show(String username, int cid) {
		User user = userRepo.findByUsername(username);
		Optional<ProductComment> opt = commentRepo.findById(cid);
		if (opt.isPresent()) {
			ProductComment comment = opt.get();
			if (user != null) {
				return comment;
			}
		}
		return null;
	}

	@Override
	public ProductComment create(String username, ProductComment comment, int pid) {
		User user = userRepo.findByUsername(username);
		Optional<Product> prodOpt = prodRepo.findById(pid);

		if (user != null && prodOpt.isPresent()) {
			comment.setProduct(prodOpt.get());
			return commentRepo.saveAndFlush(comment);
		}
		return null;
	}

	@Override
	public ProductComment update(String username, int pcid, ProductComment comment) {
		User user = userRepo.findByUsername(username);
		Optional<ProductComment> opt = commentRepo.findById(pcid);
		if (opt.isPresent()) {
			ProductComment updated = opt.get();
			updated.setDateCreated(LocalDateTime.now());
			updated.setTitle(comment.getTitle());
			updated.setDescription(comment.getDescription());
			updated.setRating(comment.getRating());
			if (user != null) {
				commentRepo.saveAndFlush(updated);
				return comment;
			}
		}
		return null;
	}

	@Override
	public boolean destroy(String username, int cid) {
		User user = userRepo.findByUsername(username);
		Optional<ProductComment> opt = commentRepo.findById(cid);
		if (opt.isPresent()) {
			if (user != null) {
				ProductComment updated = new ProductComment();
				updated = opt.get();
				updated.removeComments();
				update(user.getUsername(), cid, opt.get());
				try {
					commentRepo.deleteById(cid);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}