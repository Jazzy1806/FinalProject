package com.skilldistillery.treattracker.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.skilldistillery.treattracker.entities.User;
import com.skilldistillery.treattracker.repositories.UserRepository;

@Service
public class UserAndGroupService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserRepository userRepo;
	
	
	public List<Map<String, Object>> fetchAll(int groupId, int myId) {
		List<Map<String, Object>> getAllUser = jdbcTemplate.queryForList("select u.* from user u join group_member gm on u.id=gm.user_id"
													+ " join squad s on gm.squad_id=s.id where s.id=? and u.id != ?", groupId, myId);
		return getAllUser;
	}
	public List<Map<String, Object>> fetchAllGroup(String username) {
		User user = userRepo.findByUsername(username);
		List<Map<String, Object>> getAllGroups = jdbcTemplate.queryForList("SELECT s.* FROM squad s JOIN group_member gm " +
											"ON gm.squad_id = s.id AND gm.user_id = ?", user.getId());
		return getAllGroups;
	}
}
