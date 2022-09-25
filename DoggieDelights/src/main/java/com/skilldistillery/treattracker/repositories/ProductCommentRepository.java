package com.skilldistillery.treattracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.ProductComment;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Integer> {

	public List<ProductComment> findByProductId(int pcid);
	
	public ProductComment findByIdAndProductId(int pcid, int pid);
	
	public List<ProductComment> findByProduct_Id(int pcid);

}
