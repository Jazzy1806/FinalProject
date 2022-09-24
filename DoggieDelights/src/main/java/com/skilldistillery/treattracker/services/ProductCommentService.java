package com.skilldistillery.treattracker.services;

import java.util.List;

import com.skilldistillery.treattracker.entities.ProductComment;

public interface ProductCommentService {

	public List<ProductComment> index(String username);

	public ProductComment show(String username, int cid);

	public ProductComment create(String username, ProductComment comment);

	public ProductComment update(String username, int pcid, ProductComment comment);

	public boolean destroy(String username, int pcid);
}