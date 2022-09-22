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

class PetTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Pet pet;

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
		pet = em.find(Pet.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		pet = null;
	}

	@Test
	void test_Pet_entity_mapping() {
		assertNotNull(pet);
		assertEquals("Specter", pet.getName());
		assertEquals(70.00, pet.getWeight());
		assertEquals("male", pet.getGender());
	}

	@Test
	void test_Pet_ManyToMany_Breed_mapping() {
		assertNotNull(pet);
		assertTrue(pet.getBreeds().size() > 0);
	}

	@Test
	void test_Pet_ManyToMany_Diet_mapping() {
		assertNotNull(pet);
		assertTrue(pet.getDietNeeds().size() > 0);
	}

}
