package com.skilldistillery.treattracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Store;
import com.skilldistillery.treattracker.entities.StoreComment;

public interface StoreCommentRepository extends JpaRepository<StoreComment, Integer> {
	List<StoreComment> findByStore(Store store);

}
