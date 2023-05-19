package com.model2.mvc.service.domain;

public class Chatroom {
	private int chatRoomNo;
	private String chatroomName;


	public Chatroom(){
	}
	
	public int getChatRoomNo() {
		return chatRoomNo;
	}

	public void setChatRoomNo(int chatRoomNo) {
		this.chatRoomNo = chatRoomNo;
	}

	public String getChatroomName() {
		return chatroomName;
	}

	public void setChatroomName(String chatroomName) {
		this.chatroomName = chatroomName;
	}
}
