package com.skilldistillery.treattracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	

}
