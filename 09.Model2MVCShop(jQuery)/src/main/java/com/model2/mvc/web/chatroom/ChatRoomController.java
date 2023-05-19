package com.model2.mvc.web.chatroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model2.mvc.service.chatroom.ChatroomService;
import com.model2.mvc.service.domain.*;

@Controller
@RequestMapping("/chatroom/*")
public class ChatRoomController {
	@Autowired
	@Qualifier("chatroomServiceImpl")
	private ChatroomService chatroomService;
	
	public ChatRoomController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value="addChatroom", method=RequestMethod.GET )
	public String addChatroom() throws Exception{
	
		System.out.println("/chatroom/addChatroom");
		
		return "redirect:/chatroom/addChatroom.jsp";
	}
	
	@RequestMapping( value="addChatroom", method=RequestMethod.POST )
	public String addChatroom(@ModelAttribute("chatroom") Chatroom chatroom) throws Exception{
	
		System.out.println("/chatroom/addChatroom");
		chatroomService.addChatroom(chatroom);
		return "forward:/chatroom/goodwork.jsp";
	}
	
	@RequestMapping( value="listChatroom", method=RequestMethod.GET )
	public String listChatroom() throws Exception{
	
		System.out.println("/chatroom/listChatroom : GET");
		
		return "redirect:/chatroom/listChatroom.jsp";
	}
	
}
