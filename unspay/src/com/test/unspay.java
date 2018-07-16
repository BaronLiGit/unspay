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
@Controller

public class unspay {
	String merchantKey = "123456" ;
	/**
	 * 1.4 订单状态查询接口
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryOrderStatus")
	public String queryBalance(HttpServletRequest request,Model model) throws Exception{
		String queryBalanceUrl = "http://180.166.114.155/unspay/page/linkbank/netQueryTransStatus.action";
		StringBuffer sf = new StringBuffer();
		sf.append("merchantId=").append(request.getParameter("merchantId"));
		sf.append("&orderId=").append(request.getParameter("orderId"));
		sf.append("&merchantKey=").append(merchantKey);
		
		String mac = Md5Encrypt.md5(sf.toString()).toUpperCase();
		
		HashMap<String, String> param = new HashMap<String, String>();  
        param.put("merchantId", request.getParameter("merchantId")); 
        param.put("orderId", request.getParameter("orderId")); 
        param.put("mac", mac);
       
	 	String result = HttpFormParam.doPost(queryBalanceUrl, param);
	 	System.out.println("接口返回》》"+result);
	 	String[] array = result.split("\\|");
	 	String merchantId = array[0];
	 	String orderId = array[1];
	 	String amount = array[2];
	 	String returnCode = array[3];
	 	String returnMessage = array[4];
	 	String status = array[5];
	 	String mac11 = array[6];
	 	//String fee = array[7];
	 	StringBuffer sf1 = new StringBuffer();
		sf1.append(merchantId).append("|");
		sf1.append(orderId).append("|");
		sf1.append(amount).append("|");
		sf1.append(returnCode).append("|");
		sf1.append(returnMessage).append("|");
		sf1.append(status).append("|");
		sf1.append(merchantKey);
		System.out.println("加密前》》"+sf1.toString());
		String macquery = Md5Encrypt.md5(sf1.toString()).toUpperCase();
		System.out.println("接受到的mac》》"+mac11);
		System.out.println("验签mac》》"+macquery);
		if(macquery.equals(mac11)){
			System.out.println("验签成功");
		}else {
			System.out.println("验签失败");
		}
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
		System.out.println("结果通知:"+param);
		
		return "ok";
	}
	/**
	 * 接受结果通知 form 前台
	 * @param responseMode
	 * @throws Exception
	 */
	@RequestMapping(value="/callBackform/front")
	public @ResponseBody void responseCallBackForm(ResponseMode responseMode) throws Exception {
		
		if("1".equals(responseMode.getResponseMode())){
			System.out.println("前台通知>>>"+responseMode.toString());
			StringBuffer sf = new StringBuffer();
			sf.append("merchantId=").append(responseMode.getMerchantId());
			sf.append("&responseMode=").append(responseMode.getResponseMode());
			sf.append("&orderId=").append(responseMode.getOrderId());
			sf.append("&currencyType=").append(responseMode.getCurrencyType());
			sf.append("&amount=").append(responseMode.getAmount());
			sf.append("&returnCode=").append(responseMode.getReturnCode());
			sf.append("&returnMessage=").append(responseMode.getReturnMessage());
			sf.append("&merchantKey=").append(merchantKey);
			
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
	@RequestMapping(value = "/callBackform/back")
	public @ResponseBody void responseCallBackForm1(ResponseMode responseMode) throws Exception {
		if("2".equals(responseMode.getResponseMode())){
			System.out.println("后台通知>>>"+responseMode.toString());
			StringBuffer sf = new StringBuffer();
			sf.append("merchantId=").append(responseMode.getMerchantId());
			sf.append("&responseMode=").append(responseMode.getResponseMode());
			sf.append("&orderId=").append(responseMode.getOrderId());
			sf.append("&currencyType=").append(responseMode.getCurrencyType());
			sf.append("&amount=").append(responseMode.getAmount());
			sf.append("&returnCode=").append(responseMode.getReturnCode());
			sf.append("&returnMessage=").append(responseMode.getReturnMessage());
			sf.append("&merchantKey=").append(merchantKey);
			
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
	
	/**
	 * 
	 */
	@RequestMapping(value = "/payRequest")
	public String unspaySelect(String status){
		if ("pay".equals(status)){
			return "payRequest";
		}else
		if("query".equals(status)){
			return "queryOrderStatus";
		}else{
			return "";
		}
	}
}
