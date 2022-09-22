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

class DietTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Diet diet;

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
		diet = em.find(Diet.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		diet = null;
	}

	@Test
	void test_Diet_entity_mapping() {
		assertNotNull(diet);
		assertEquals("Wild Game", diet.getName());
	}

	@Test
	void test_Diet_ManyToMany_Pet_mapping() {
		assertNotNull(diet);
		assertTrue(diet.getPets().size() > 0);
	}

}
