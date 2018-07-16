package com.test;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.test.Md5Encrypt;
import com.test.HttpFormParam;
@Controller

public class delegateFrontPay {
	String key = "123456" ;
	String accountId = "";
	/**
	 * 实时代付订单支付接口
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delegatePay")
	public String responseCollect(HttpServletRequest request,Model model) throws Exception{
		//测试地址
		String myurl = "http://180.166.114.155:7181/delegate-pay-front-dp/delegatePay/fourElementsPay";
		//正式地址
		//String myurl = "http://pay.unspay.com:8081/delegate-pay-front/delegatePay/pay";
		accountId = request.getParameter("accountId");
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(request.getParameter("accountId"));
		sf.append("&name=").append(request.getParameter("name"));
		sf.append("&cardNo=").append(request.getParameter("cardNo"));
		sf.append("&orderId=").append(request.getParameter("orderId"));
		sf.append("&purpose=").append(request.getParameter("purpose"));
		sf.append("&amount=").append(request.getParameter("amount"));
		sf.append("&idCardNo=").append(request.getParameter("idCardNo"));
		sf.append("&summary=").append(request.getParameter("summary"));
		if(request.getParameter("phoneNo")!="" && request.getParameter("phoneNo")!=null){
			sf.append("&phoneNo=").append(request.getParameter("phoneNo"));
		}
		if(request.getParameter("responseUrl")!=""&& request.getParameter("phoneNo")!=null){
			sf.append("&responseUrl=").append(request.getParameter("responseUrl"));
		}
		sf.append("&key=").append(key);
		
		//System.out.println(sf);
		
		String mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
		
		 HashMap<String, String> param = new HashMap<String, String>();  
	        param.put("accountId", request.getParameter("accountId")); 
	        param.put("name", request.getParameter("name")); 
	        param.put("cardNo", request.getParameter("cardNo")); 
	        param.put("orderId", request.getParameter("orderId")); 
	        param.put("purpose", request.getParameter("purpose")); 
	        param.put("amount", request.getParameter("amount"));
	        param.put("idCardNo", request.getParameter("idCardNo"));
	        param.put("summary", request.getParameter("summary"));
	        param.put("phoneNo", request.getParameter("phoneNo"));
	        param.put("responseUrl", request.getParameter("responseUrl")); 
	        param.put("mac", mac);
	       
		 	String result = HttpFormParam.doPost(myurl, param);
		 	System.out.println(""+result);
		 	model.addAttribute("msg", result);
		 	return "result";
		 	
	}
	/**
	 * 试试代付订单状态查询
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryOrderStatus")
	public String queryOrderStatus(HttpServletRequest request,Model model) throws Exception{
		String myurl = "http://180.166.114.155:7181/delegate-pay-front-dp/delegatePay/queryOrderStatus";
		accountId = request.getParameter("accountId");
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(request.getParameter("accountId"));
		sf.append("&orderId=").append(request.getParameter("orderId"));
		sf.append("&key=").append(key);
		String mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
		HashMap<String,String> param = new HashMap<String,String>();
		param.put("accountId", accountId);
		param.put("orderId", request.getParameter("orderId"));
		param.put("mac", mac);
		String result = HttpFormParam.doPost(myurl, param);
		System.out.println(""+result);
		model.addAttribute("msg", result);
		return "result";
	}
	/**
	 * 账户余额保证金余额查询
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryBalance")
	public String queryBalance(HttpServletRequest request,Model model) throws Exception{
		String queryBalanceUrl = "http://180.166.114.155:7181/delegate-pay-front-dp/delegatePay/queryBlance";
		//String queryBalanceUrl = "http://pay.unspay.com:8081/delegate-pay-front/delegatePay/queryBlance";
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(request.getParameter("accountId"));
		sf.append("&key=").append(key);
		String mac = Md5Encrypt.md5(sf.toString()).toUpperCase();
		HashMap<String,String> param = new HashMap<String,String>();
		param.put("accountId", request.getParameter("accountId"));
		param.put("mac", mac);
		String result = HttpFormParam.doPost(queryBalanceUrl, param);
		System.out.println(""+result);
		model.addAttribute("msg", result);
		return "result";
	}
	/**
	 * 接收异步结果通知 json
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/callBack")
	@ResponseBody
	public String responseCallBack(@RequestBody Map<String,String> param) {
		System.out.println("json");
		System.out.println("接口实时代付结果通知:"+param);
		
		return "ok";
	}
	/**
	 * 接受一步结果通知 form
	 * @param responseMode
	 * @throws Exception
	 */
	@RequestMapping(value="/callBackform")
	public @ResponseBody void responseCallBackForm(ResponseMode responseMode) throws Exception {
		System.out.println("结果通知>>>"+responseMode.toString());
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(accountId);
		sf.append("&orderId=").append(responseMode.getOrderId());
		sf.append("&amount=").append(responseMode.getAmount());
		sf.append("&result_code=").append(responseMode.getResult_code());
		sf.append("&result_msg=").append(responseMode.getResult_msg());
		sf.append("&key=").append(key);
		
		System.out.println("验签串>>>："+sf.toString());
		String responseMac=responseMode.getMac();
		System.out.println("接收到的MAC>>>"+responseMac);
		String mac=Md5Encrypt.md5(sf.toString()).toUpperCase();
		System.out.println("加密MAC>>>"+mac);
		if (responseMac.equals(mac)) {
			System.out.println("验签成功");
		}else {
			System.out.println("验签失败");
		}
	}
}
