package com.model2.mvc.service.chatroom.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.chatroom.ChatroomDao;
import com.model2.mvc.service.domain.Chatroom;

@Repository("chatroomDaoImpl")
public class ChatroomDaoImpl implements ChatroomDao {
	//sqlsession¿«¾îÁÜ
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public void addChatroom(Chatroom chatroom) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(chatroom);
		sqlSession.insert("ChatroomMapper.addChatroom", chatroom);
	}

	@Override
	public List<Chatroom> getProductList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
