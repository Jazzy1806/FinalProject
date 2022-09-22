package com.skilldistillery.treattracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductCommentTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private ProductComment comment;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPATreatTracker");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		comment = em.find(ProductComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comment = null;
	}

	@Test
	void test_ProductComment_entity_mapping() {
		assertNotNull(comment);
		assertEquals("Product Comment 1", comment.getTitle());
	}
	
	@Test
	void test_ProductComment_ManyToMany_Ingredient_mapping() {
		assertNotNull(comment);
		assertNotNull(comment.getProduct());
	}

}
