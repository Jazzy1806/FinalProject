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

class ChainTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Chain chain;

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
		chain = em.find(Chain.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		chain = null;
	}

	@Test
	void test_Chain_entity_mapping() {
		assertNotNull(chain);
		assertEquals("Petco", chain.getName());
	}
	
	@Test
	void test_Chain_OneToMany_Store_mapping() {
		assertNotNull(chain);
		assertNotNull(chain.getStores());
	}

}
