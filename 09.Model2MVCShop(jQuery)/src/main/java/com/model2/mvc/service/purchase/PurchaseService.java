package com.model2.mvc.service.purchase;

import java.util.Map;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.*;

public interface PurchaseService {
	public Purchase addPurchase(Purchase purchaseVO) throws Exception;
	public Purchase getPurchase(int pur_no) throws Exception;
	public Map<String, Object> getPurchaseList(Search searchVO,String userId) throws Exception;
	//public Map<String, Object> getSaleList(Search searchVO) throws Exception;
	public Purchase updatePurchase(Purchase purchaseVO) throws Exception;
	public void updateTranCode(Purchase purchaseVO) throws Exception;
	public void updateTranCodeByProd(int prodNo, String tranCode) throws Exception ;
}
