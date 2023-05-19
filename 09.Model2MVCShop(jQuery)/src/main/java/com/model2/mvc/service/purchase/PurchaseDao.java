package com.model2.mvc.service.purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.domain.*;

public interface PurchaseDao {
	
	public void insertPurchase(Purchase purchaseVO) throws Exception;
	
	public Purchase findPurchase(int tran_no) throws Exception;
	
	public int getTotalCount(String userId) throws Exception ;
	
	public void updatePurchase(Purchase purchaseVO) throws Exception;
	
	public void updateTranCodeByProd(int no, String tranCode) throws Exception;
	
	public void updateTranCode(Purchase purchaseVO) throws Exception;

	public List<Purchase> getPurchaseList(Search search, String userId) throws Exception;

}
