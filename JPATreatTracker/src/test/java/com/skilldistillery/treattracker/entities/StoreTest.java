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

class StoreTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Store store;

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
		store = em.find(Store.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		store = null;
	}

	@Test
	void test_Store_entity_mapping() {
		assertNotNull(store);
		assertEquals("Petco on Ken Pratt", store.getName());
	}
	
	@Test
	void test_Store_OneToMany_Inventory_mapping() {
		assertNotNull(store);
		assertTrue(store.getInventories().size() > 0);
		System.out.println(store.getInventories());
	}
	@Test
	void test_Store_OneToMany_ProductReport_mapping() {
		assertNotNull(store);
		assertTrue(store.getProductReports().size() > 0);
	}
	
	@Test
	void test_store_to_user_many_to_one_relationship() {
		assertNotNull(store.getUser());
	}

}
