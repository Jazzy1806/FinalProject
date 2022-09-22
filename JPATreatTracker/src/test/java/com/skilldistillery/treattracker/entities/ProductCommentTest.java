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

	private ProductComment productComment;

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

		productComment = em.find(ProductComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();

		productComment = null;
	}

	@Test
	void test_ProductComment_entity_mapping() {
		assertNotNull(productComment);
		assertEquals("Product Comment 1", productComment.getTitle());
	}
	
	@Test
	void test_ProductComment_ManyToMany_Ingredient_mapping() {
		assertNotNull(productComment);
		assertEquals("Product comment 1", productComment.getTitle());
		assertEquals("Description 1", productComment.getDescription());
		assertEquals(1, productComment.getRating());
	}

	@Test
	void test_ProductComment_ManyToOne_ProductComponent_mapping() {
		assertNotNull(productComment);
		assertEquals("Description 1", productComment.getParentProductComment().getDescription());
	}

	@Test
	void test_ProductComment_OneToMany_ProductComponent_mapping() {
		assertNotNull(productComment);
		assertTrue(productComment.getReplyProductComments().size() > 1);
	}

}
