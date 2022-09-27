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

class ProductTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Product product;

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
		product = em.find(Product.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		product = null;
	}

	@Test
	void test_Product_entity_mapping() {
		assertNotNull(product);
		assertEquals("Pacific Catch Recipe", product.getName());
	}
	
	@Test
	void test_Product_ManyToMany_Ingredient_mapping() {
		assertNotNull(product);
		assertTrue(product.getIngredients().size() > 0);
	}
	
	@Test
	void test_Product_OneToMany_Inventory_mapping() {
		assertNotNull(product);
		assertTrue(product.getInventoryItems().size() > 0);
		System.out.println(product.getInventoryItems());
		for(Inventory item : product.getInventoryItems()) {
			System.out.println(item.toString());
		}
	}

}
