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

class StoreCommentTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	private StoreComment storeComment;

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

		storeComment = em.find(StoreComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();

		storeComment = null;
	}

	@Test
	void test_StoreComment_entity_mapping() {

		assertNotNull(storeComment);
		assertEquals("Store Comment 1", storeComment.getTitle());
	}
	
	@Test
	void test_StoreComment_ManyToMany_Ingredient_mapping() {

		assertNotNull(storeComment);
		assertEquals("Store comment 1", storeComment.getTitle());
		assertEquals("Description 1", storeComment.getDescription());
		assertEquals(1, storeComment.getRating());
	}

	@Test
	void test_StoreComment_ManyToOne_ProductComponent_mapping() {
		assertNotNull(storeComment);
		assertEquals("Description 1", storeComment.getParentStoreComment().getDescription());
	}

	@Test
	void test_StoreComment_OneToMany_ProductComponent_mapping() {
		assertNotNull(storeComment);
		assertTrue(storeComment.getReplyStoreComments().size() > 1);
	}

}
