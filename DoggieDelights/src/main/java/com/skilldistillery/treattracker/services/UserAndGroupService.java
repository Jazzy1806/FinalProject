package com.skilldistillery.treattracker.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserAndGroupService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public List<Map<String, Object>> fetchAll(int groupId, int myId) {
		List<Map<String, Object>> getAllUser = jdbcTemplate.queryForList("select u.* from user u join group_member gm on u.id=gm.user_id"
													+ " join squad s on gm.squad_id=s.id where s.id=? and u.id != ?", groupId, myId);
		return getAllUser;
	}
	public List<Map<String, Object>> fetchAllGroup(int groupId) {
		List<Map<String, Object>> getAllGroups = jdbcTemplate.queryForList("SELECT s.* FROM squad s JOIN group_member gm " +
											"ON gm.squad_id = s.id AND gm.user_id = ?", groupId);
		return getAllGroups;
	}
}
