package com.model2.mvc.service.chatroom;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Chatroom;

public interface ChatroomDao {
	
	public void addChatroom(Chatroom chatroom) throws Exception ;

	public List<Chatroom> getProductList(Search search) throws Exception ;
}
