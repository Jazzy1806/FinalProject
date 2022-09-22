package com.skilldistillery.treattracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductReportTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private ProductReport productReport;

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
		productReport = em.find(ProductReport.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		productReport = null;
	}

	@Test
	void test_ProductReport_entity_mapping() {
		assertNotNull(productReport);
		assertEquals(9.99, productReport.getPrice());
		assertEquals("Finally!", productReport.getRemark());
	}

	@Test
	void test_ProductReport_ManyToOne_User_mapping() {
		assertNotNull(productReport);
		assertEquals("admin", productReport.getUser().getUsername());
	}

	@Test
	void test_ProductReport_ManyToOne_Product_mapping() {
		assertNotNull(productReport);
		assertEquals("Pacific Catch Recipe", productReport.getProduct().getName());
	}

	@Test
	void test_ProductReport_ManyToOne_Store_mapping() {
		assertNotNull(productReport);
		assertEquals("Petco on Ken Pratt", productReport.getStore().getName());
	}

}
