package com.niit.bridge.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.bridge.model.Message;
import com.niit.bridge.model.OutputMessage;
import com.niit.bridge.model.UserDetails;



@Controller
public class ChatController {

	//Logger log = Logger.getLogger(ChatController.class);
	
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message) {
		System.out.println("Calling the method sendMessage().");
		
		
	//	message.setUserId(userId);
		System.out.println("Message : "+message.getMessage());
		
		System.out.println("Message ID : "+message.getId());
System.out.println("Message userId: "+message.getUserId());
				
		return new OutputMessage(message, new Date());

	}
	
}
