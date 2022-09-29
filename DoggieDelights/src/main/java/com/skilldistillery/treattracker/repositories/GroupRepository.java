package com.skilldistillery.treattracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.treattracker.entities.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {


}
