package com.model2.mvc.service.chatroom.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.chatroom.ChatroomDao;
import com.model2.mvc.service.chatroom.ChatroomService;
import com.model2.mvc.service.domain.Chatroom;


@Service("chatroomServiceImpl")
public class ChatroomServiceImpl implements ChatroomService {
	@Autowired
	@Qualifier("chatroomDaoImpl")
	private ChatroomDao chatroomDao;
	
	@Override
	public void addChatroom(Chatroom chatroom) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(chatroom.getChatroomName());
		chatroomDao.addChatroom(chatroom);
	
	}

	@Override
	public Map<String, Object> getChatroomList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
