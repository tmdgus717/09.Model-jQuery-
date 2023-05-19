package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public PurchaseDaoImpl() {
		System.out.println(this.getClass());
	}
	@Override
	public void insertPurchase(Purchase purchase) throws Exception { //end
		// TODO Auto-generated method stub
		sqlSession.insert("PurchaseMapper.insertPurchase", purchase);
	}

	@Override
	public Purchase findPurchase(int tran_no) throws Exception { //end
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tran_no);
	}

	@Override
	public List<Purchase> getPurchaseList(Search search,String userId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("search", search);
		System.out.println("hihihidao");
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
	}
	
	public int getTotalCount(String userId) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", userId);
	}
	@Override
	public void updatePurchase(Purchase purchase) throws Exception { //end
		System.out.println("´Ù¿ÀÀÔ´Ï´Ù¿Ë:"+purchase);
		//ÇØ¾ßµÊ
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	@Override
	public void updateTranCodeByProd(int prodNo, String tranCode) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("prodNo", prodNo);
		map.put("tranCode", tranCode);
		// ÇØ¾ßµÊ
		sqlSession.update("PurchaseMapper.updateTranCodeByProd", map);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		// ÇØ¾ßµÊ
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
	}

}
