package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;

@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	//생성자 없이 생성 reflection api!!
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	
	public PurchaseController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit; //3
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize; //5
	
	@RequestMapping("addPurchase")
	public String addPurchase(@ModelAttribute("purchase") Purchase purchase,
								@RequestParam("prodNo") int prodNo,
								@RequestParam("buyerId") String buyerId) throws Exception{
		System.out.println(prodNo);
		String tmp_date=purchase.getDivyDate();
		String date = tmp_date.replaceAll("-","");
		
		Purchase purchase2 = purchase;
		purchase2.setBuyer(userService.getUser(buyerId));
		purchase2.setPurchaseProd(productService.getProduct(prodNo));
		purchase2.setTranCode("1");
		purchase2.setDivyDate(date);
		System.out.println(purchase2);
		purchaseService.addPurchase(purchase2);
		return "forward:/purchase/addPurchase.jsp";
	}
	@RequestMapping("addPurchaseView")
	public String addPurchaseView(@RequestParam("prodNo") int prodNo,User uvo, Model model,HttpServletRequest request) throws Exception{
		
		Product product = productService.getProduct(prodNo);
		System.out.println(product);
		
		HttpSession session = request.getSession();
		uvo = (User)session.getAttribute("user");
		System.out.println(uvo);
		Purchase purchase = new Purchase();
		purchase.setPurchaseProd(product);
		purchase.setBuyer(uvo);
		
		model.addAttribute("purchase",purchase);
		System.out.println(purchase);
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	@RequestMapping("getPurchase")
	public String getPurchase(@RequestParam("tranNo") String tranNo, Model model ) throws Exception{
		int tranNoInt = Integer.parseInt(tranNo);
		
		Purchase purchase = purchaseService.getPurchase(tranNoInt);
		model.addAttribute("purchase", purchase);
		
		return "forward:/purchase/getPurchase.jsp";
	}
	
	@RequestMapping("updatePurchaseView")
	public String updatePurchaseView( @RequestParam("tranNo") int tranNo, Model model ) throws Exception{

		System.out.println("/updatePurchaseView.do");
	
	
		Purchase purchase = purchaseService.getPurchase(tranNo);

		model.addAttribute("purchase", purchase);
		
		return "forward:/purchase/updatePurchaseView.jsp";
	}
	
	@RequestMapping("updatePurchase")
	public String updatePurchase( @ModelAttribute("purchase") Purchase purchase) throws Exception{

		System.out.println("/updatePurchase.do");
		System.out.println(purchase);
		//Business Logic
		purchaseService.updatePurchase(purchase);
		return "redirect:/purchase/getPurchase?tranNo="+purchase.getTranNo();
	}
	
	@RequestMapping("listPurchase")
	public String listPurchase( @ModelAttribute("search") Search search , User user,Model model , HttpServletRequest request) throws Exception{
		System.out.println("hihihi listpurchase");
	
		System.out.println(search);
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		HttpSession session = request.getSession();
		user=(User)session.getAttribute("user");
		System.out.println("hihihi");
		// Business logic 수행
		Map<String , Object> map=purchaseService.getPurchaseList(search,user.getUserId());
		System.out.println("hihihi");
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/purchase/listPurchase.jsp";
	}
	
	@RequestMapping("updateTranCode")
	public String updateTranCode( @RequestParam("tranNo") int tranNo,@RequestParam("tranCode") String tranCode , HttpSession session) throws Exception{

		System.out.println("/updatePurchase.do");
		//Business Logic
		Purchase purchaseVO=new Purchase();
		purchaseVO.setTranNo(tranNo);
		purchaseVO.setTranCode(tranCode);
		purchaseService.updateTranCode(purchaseVO);
		
		return "redirect:/listPurchase.do";
	}
	
	@RequestMapping("updateTranCodeByProd")
	public String updateTranCodeByProd( @RequestParam("prodNo") int prodNo ,@RequestParam("tranCode") String tranCode, HttpSession session) throws Exception{
		//prodNo=${product.prodNo}&tranCode=2
		System.out.println("/updatePurchase.do");
		//Business Logic
		System.out.println(prodNo);
		System.out.println(tranCode);
		purchaseService.updateTranCodeByProd(prodNo, tranCode);
		
		return "redirect:/listProduct.do?menu=manage";
	}
}//end ProductController class
