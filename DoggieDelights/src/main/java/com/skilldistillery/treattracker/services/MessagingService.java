package com.skilldistillery.treattracker.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.skilldistillery.treattracker.entities.Group;
import com.skilldistillery.treattracker.entities.Message;
import com.skilldistillery.treattracker.entities.MessageGroup;
import com.skilldistillery.treattracker.repositories.GroupRepository;

@Service
public class MessagingService {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private GroupRepository groupRepo;
	
	
	public void sendMessage(String to, Message message) {
		jdbcTemplate.update("INSERT INTO message (message_content, message_from, message_to, created)" +
							" VALUES(?, ?, ?, current_time)", message.getMessage(), message.getFromUser().getId(), to);
		
		simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
	}
	
	
//	public List<Map<String, Object>> getListMessage(@PathVariable("from") Integer from, @PathVariable("to") Integer to) {
	public List<Map<String, Object>> getMessages(@PathVariable("from") Integer from, @PathVariable("to") Integer to) {
		return jdbcTemplate.queryForList("SELECT * from message where (message_from = ? AND message_to = ?)" +
				" OR (message_to = ? AND message_from = ?) ORDER BY created ASC", from, to, from, to);
	}
	
	
//	public List<Map<String, Object>> getListMessageGroups(@PathVariable("groupid") Integer groupid) {
	public List<Map<String, Object>> getGroupMessages(@PathVariable("groupid") Integer groupid) {
			return jdbcTemplate.queryForList("SELECT gm.*, us.username AS name FROM group_message gm JOIN user us ON" +
						" us.id = gm.user_id WHERE gm.squad_id =? ORDER BY created ASC", groupid);
	}
	
	
	public void sendMessageGroup(Integer to, MessageGroup message) {
		jdbcTemplate.update("INSERT INTO group_message(squad_id, user_id, message, created) " +
				"VALUES(?, ?, ?, current_timestamp)", to, message.getUser().getId(), message.getMessage());
		Optional<Group> groupOpt = groupRepo.findById(to);
		if (groupOpt.isPresent()) {
			Group group = groupOpt.get();
		message.setGroup(group);
		simpMessagingTemplate.convertAndSend("/topic/messages/group/" + to, message);
		}
	}
}
