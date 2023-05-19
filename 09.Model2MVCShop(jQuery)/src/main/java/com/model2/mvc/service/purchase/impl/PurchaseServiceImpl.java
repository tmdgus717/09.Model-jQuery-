package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;


@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDAO;
	
	public PurchaseServiceImpl() {
		System.out.println(this.getClass());
	}
	@Override
	public Purchase addPurchase(Purchase purchaseVO) throws Exception {
		purchaseDAO.insertPurchase(purchaseVO);
		return purchaseVO;
	}//¿Ï·á

	@Override
	public Purchase getPurchase(int pur_no) throws Exception {
		return purchaseDAO.findPurchase(pur_no);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search,String userId) throws Exception {
		System.out.println("hihihi2");
		List<Purchase> list= purchaseDAO.getPurchaseList(search,userId);
		System.out.println("hihihi3");
		int totalCount = purchaseDAO.getTotalCount(userId);
		System.out.println("hihihi4");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list",list);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}
//
//	@Override
//	public HashMap<String, Object> getSaleList(Search searchVO) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Purchase updatePurchase(Purchase purchaseVO) throws Exception {		
		purchaseDAO.updatePurchase(purchaseVO);
		return purchaseVO;
	}

	@Override
	public void updateTranCode(Purchase purchaseVO) throws Exception {
		purchaseDAO.updateTranCode(purchaseVO);
	}
	
	public void updateTranCodeByProd(int prodNo, String tranCode) throws Exception {
		purchaseDAO.updateTranCodeByProd(prodNo,tranCode);
	}
	
}