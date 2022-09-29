package com.skilldistillery.treattracker.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.treattracker.entities.Message;
import com.skilldistillery.treattracker.entities.MessageGroup;
import com.skilldistillery.treattracker.services.MessagingService;
import com.skilldistillery.treattracker.services.UserAndGroupService;

@RestController
@CrossOrigin({ "*", "http://localhost:4205" })
public class MessageController {

	@Autowired
	MessagingService messageService;
	
	@Autowired
	UserAndGroupService uagService;
	
	
	
	@MessageMapping("/chat/{to}")
	public void sendPersonalMessage(@DestinationVariable String to, Message message) {
		messageService.sendMessage(to,  message);
	}
	
	
//	public List<Map<String, Object>> getListMessageChat(@PathVariable("from") Integer from, @PathVariable("to") Integer to) {
	@GetMapping("listmessage/{from}/{to}")
	public List<Map<String, Object>> loadMessages(@PathVariable("from") Integer from, @PathVariable("to") Integer to) {
		return messageService.getMessages(from, to);
	}
	
	
	@MessageMapping("/chat/group/{to}")
	public void sendMessageToGroup(@DestinationVariable Integer to, MessageGroup message) {
		messageService.sendMessageGroup(to, message);
	}
	
	
//	public List<Map<String, Object>> getListMessageGroupChat(@PathVariable("groupid") Integer groupid) {
	@GetMapping("listmessage/group/{groupid}")
	public List<Map<String, Object>> loadGroupMessages(@PathVariable("groupid") Integer groupid) {
		return messageService.getGroupMessages(groupid);
	}
	
	
//	@GetMapping("/fetchAllUsers/{myId}")
//	public List<Map<String, Object>> fetchAll(@PathVariable("myId") String myId) {
	@GetMapping("/fetchGroupUsers/{groupId}/{myId}")
	public List<Map<String, Object>> fetchUsers(@PathVariable("groupId") int groupId, @PathVariable("myId") int myId) {
		return uagService.fetchAll(groupId, myId);
	}

	
//	@GetMapping("/fetchAllGroups/{groupid}")
//	public List<Map<String, Object>> fetchAllGroup(@PathVariable("groupid") String groupid) {
	@GetMapping("/fetchGroups/{myId}")
	public List<Map<String, Object>> fetchGroups(@PathVariable("myId") int myId, Principal principal) {
		return uagService.fetchAllGroup(principal.getName());
	}
	
	
}
