package com.model2.mvc.service.chatroom;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Chatroom;

public interface ChatroomService {
	public void addChatroom(Chatroom chatroom) throws Exception;
	
	public Map<String, Object> getChatroomList(Search search) throws Exception;
}
