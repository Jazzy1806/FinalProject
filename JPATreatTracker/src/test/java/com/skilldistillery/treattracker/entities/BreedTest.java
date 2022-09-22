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

class BreedTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Breed breed;

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
		breed = em.find(Breed.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		breed = null;
	}

	@Test
	void test_Breed_entity_mapping() {
		assertNotNull(breed);
		assertEquals("Husky", breed.getName());
		assertEquals("white-black", breed.getColor());
	}

	@Test
	void test_Breed_ManyToMany_Pet_mapping() {
		assertNotNull(breed);
		assertTrue(breed.getPets().size() > 0);
	}

}
