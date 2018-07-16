package com.test;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.util.HttpFormParam;
import com.util.Md5Encrypt;
@Controller

public class delegateFrontPay {
	String key = "123456" ;
	/**
	 * 协议支付接口
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 * 11141
	 */
	@RequestMapping(value="/prePay")
	public String prePay(HttpServletRequest request,Model model) throws Exception{
		
		
		String accountId=request.getParameter("accountId");
		String customerId=request.getParameter("customerId");
		String orderNo=request.getParameter("orderNo");
		String purpose=request.getParameter("purpose");
		String amount=request.getParameter("amount");
		String commodityName=request.getParameter("commodityName");
		String businessType=request.getParameter("businessType");
	    String responseUrl=request.getParameter("responseUrl");
	    String pageResponseUrl=request.getParameter("pageResponseUrl");
	    String mac="";
	    key=request.getParameter("key");
	    String myurl = "http://waptest.unspay.com:18083/quickpay-front/quickPayWapNucc/prePay";
	    StringBuffer sf = new StringBuffer();
	    sf.append("accountId=").append(accountId);
	    sf.append("&customerId=").append(customerId);
	    sf.append("&orderNo=").append(orderNo);
	    sf.append("&purpose=").append(purpose);
	    sf.append("&amount=").append(amount);
	    sf.append("&commodityName=").append(commodityName);
	    sf.append("&businessType=").append(businessType);
	    sf.append("&responseUrl=").append(responseUrl);
	    sf.append("&pageResponseUrl=").append(pageResponseUrl);
	    sf.append("&key=").append(key);
	    System.out.println("加密前+++++++++"+sf.toString());
		mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
		
	     System.out.println("mac++++++++++"+mac);
		 HashMap<String, String> param = new HashMap<String, String>();  
	        param.put("accountId", accountId); 
	        param.put("customerId", customerId); 
	        param.put("orderNo", orderNo); 
	        param.put("purpose", purpose); 
	        param.put("amount", amount); 
	        param.put("commodityName", commodityName); 
	        param.put("businessType", businessType); 
	        param.put("responseUrl", responseUrl); 
	        param.put("pageResponseUrl", pageResponseUrl); 
	        param.put("mac", mac); 
       
		 	String result = HttpFormParam.doPost(myurl, param);
		 	System.out.println(""+result);
		 	model.addAttribute("msg", result);
		 	return "result";
		    
		 	
	}
	
	/**
	 * 退款申请接口
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/refund")
	public String refund(HttpServletRequest request,Model model) throws Exception{
		String accountId=request.getParameter("accountId");
		String orderNo=request.getParameter("orderNo");
		String oriOrderNo=request.getParameter("oriOrderNo");
		String amount=request.getParameter("amount");
		String purpose=request.getParameter("purpose");
	    String mac="";
	    key=request.getParameter("key");
	    String myurl = "http://waptest.unspay.com:18083/quickpay-front/quickPayWapNucc/refund";
	    StringBuffer sf = new StringBuffer();
	    sf.append("accountId=").append(accountId);
	    sf.append("&orderNo=").append(orderNo);
	    sf.append("&oriOrderNo=").append(oriOrderNo);
	    sf.append("&amount=").append(amount);
	    sf.append("&purpose=").append(purpose);
	    sf.append("&key=").append(key);
	    System.out.println("加密前+++++++++"+sf.toString());
		mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
		
	   System.out.println("mac++++++++++"+mac);
		 HashMap<String, String> param = new HashMap<String, String>();  
	        param.put("accountId", accountId); 
	        param.put("orderNo", accountId);
	        param.put("oriOrderNo", accountId);
	        param.put("amount", accountId);
	        param.put("purpose", accountId);
	        param.put("mac", mac);
	       
		 	String result = HttpFormParam.doPost(myurl, param);
		 	System.out.println(""+result);
		 	model.addAttribute("msg", result);
		 	return "result";
		 	
	}
	/**
	 * 协议支付订单状态查询接口
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/prePayQuery")
	public String prePayQuery(HttpServletRequest request,Model model) throws Exception{
		String accountId=request.getParameter("accountId");
		String orderNo=request.getParameter("orderNo");
		
	    String mac="";
	    key=request.getParameter("key");
	    String myurl = "http://waptest.unspay.com:18083/quickpay-front/quickPayWapNucc/refund";
	    StringBuffer sf = new StringBuffer();
	    sf.append("accountId=").append(accountId);
	    sf.append("&orderNo=").append(orderNo);
	    
	    sf.append("&key=").append(key);
	    System.out.println("加密前+++++++++"+sf.toString());
		mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
		
	   System.out.println("mac++++++++++"+mac);
		 HashMap<String, String> param = new HashMap<String, String>();  
	        param.put("accountId", accountId); 
	        param.put("orderNo", accountId);
	      
	        param.put("mac", mac);
	       
		 	String result = HttpFormParam.doPost(myurl, param);
		 	System.out.println(""+result);
		 	model.addAttribute("msg", result);
		 	
		 	return "result";
		 	
	}
	@RequestMapping(value="/forward")
	public String forward(String status) throws Exception {
		if("1".equals(status)){
			return "prePay";
		}else if ("2".equals(status)){
			return "refund";
		}else if ("3".equals(status)){
			return "prePayQuery";
		}else{
			return "result";
		}
		
	}
}
