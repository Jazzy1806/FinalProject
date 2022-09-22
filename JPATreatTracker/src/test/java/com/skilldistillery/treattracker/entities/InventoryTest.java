package com.skilldistillery.treattracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryTest {
	
	private static EntityManagerFactory emf;	
	private static EntityManager em;
	private Inventory inventory;

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
		inventory = em.find(Inventory.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		inventory = null;
	}

	@Test
	void test_Inventory_entity_mapping() {
		assertNotNull(inventory);
	}

}
