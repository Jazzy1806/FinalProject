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

class AddressTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Address address;

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
		address = em.find(Address.class, 4);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		address = null;
	}

	@Test
	void test_Address_entity_mapping() {
		assertNotNull(address);
		assertEquals("20360 Sw Westside Dr", address.getAddress());
	}
	
	@Test
	void test_Address_OneToOne_User_mapping() {
		assertNotNull(address);
		assertNotNull(address.getUser());
	}

}
