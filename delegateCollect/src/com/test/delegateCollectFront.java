package com.test;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.test.Md5Encrypt;
import net.sf.json.JSONObject;
import com.test.AppHttp;
@Controller

public class delegateCollectFront {
	String key = "123456" ;
	
	/**
	 * 子协议录入
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/signSimpleSubContract")
	public String responseCollect(HttpServletRequest request,Model model) throws Exception{
		//测试地址
		String myurl = "http://180.166.114.155:58082/delegate-collect-front/subcontract/signSimpleSubContractJson";
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(request.getParameter("accountId"));
		sf.append("&contractId=").append(request.getParameter("contractId"));
		sf.append("&name=").append(request.getParameter("name"));
		sf.append("&phoneNo=").append(request.getParameter("phoneNo"));
		sf.append("&cardNo=").append(request.getParameter("cardNo"));
		sf.append("&idCardNo=").append(request.getParameter("idCardNo"));
		sf.append("&startDate=").append(request.getParameter("startDate"));
		sf.append("&endDate=").append(request.getParameter("endDate"));
		sf.append("&key=").append(key);
		
		String mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
	
		System.out.println("result:"+mac);
	 		JSONObject obj = new JSONObject();  
	 		obj.put("cardNo",request.getParameter("cardNo") ); 
	 		obj.put("name",request.getParameter("name"));
	 		obj.put("idCardNo",request.getParameter("idCardNo")); 
	 		obj.put("phoneNo", request.getParameter("phoneNo")); 
	 		obj.put("startDate",request.getParameter("startDate")); 
	 		obj.put("endDate",request.getParameter("endDate")); 
	 		obj.put("contractId",request.getParameter("contractId"));
	 		obj.put("accountId",request.getParameter("accountId")); 
	 		obj.put("mac",mac);
	 		
	 		String result = AppHttp.appadd(myurl,obj); 
	 		System.out.println("result:"+result);
		 	model.addAttribute("msg", result);
		 	return "result";
		 	
	}
	/**
	 * 1.2 委托代扣接口
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delegateCollect")
	public String queryOrderStatus(HttpServletRequest request,Model model) throws Exception{
		String myurl = "http://180.166.114.155:58082/delegate-collect-front/delegateCollect/collectJson";
		
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(request.getParameter("accountId"));
		sf.append("&subContractId=").append(request.getParameter("subContractId"));
		sf.append("&orderId=").append(request.getParameter("orderId"));
		sf.append("&purpose=").append(request.getParameter("purpose"));
		sf.append("&amount=").append(request.getParameter("amount"));
		sf.append("&responseUrl=").append(request.getParameter("responseUrl"));
		sf.append("&key=").append(key);
		
		String mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
		
		JSONObject obj = new JSONObject();  
	 	obj.put("accountId",request.getParameter("accountId")); 
	 	obj.put("subContractId",request.getParameter("subContractId"));
	 	obj.put("orderId",request.getParameter("orderId")); 
	 	obj.put("purpose", request.getParameter("purpose")); 
	 	obj.put("amount",request.getParameter("amount")); 
	 	obj.put("responseUrl",request.getParameter("responseUrl")); 
	 	obj.put("mac",mac); 
	 	
	 	String result = AppHttp.appadd(myurl,obj); 
 		System.out.println("result:"+result);
	 	model.addAttribute("msg", result);
	 	return "result";
	}
	/**
	 * 1.4 订单状态查询接口
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryOrderStatus")
	public String queryBalance(HttpServletRequest request,Model model) throws Exception{
		String queryBalanceUrl = "http://180.166.114.155:58082/delegate-collect-front/delegateCollect/queryJson";
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(request.getParameter("accountId"));
		sf.append("&orderId=").append(request.getParameter("orderId"));
		sf.append("&key=").append(key);
		String mac = Md5Encrypt.md5(sf.toString()).toUpperCase();
		JSONObject obj = new JSONObject();  
	 	obj.put("accountId",request.getParameter("accountId")); 
	 	obj.put("orderId",request.getParameter("orderId"));
	 	obj.put("mac",mac); 
		
	 	String result = AppHttp.appadd(queryBalanceUrl,obj); 
 		System.out.println("result:"+result);
	 	model.addAttribute("msg", result);
	 	return "result";	
	 	}
	/**
	 * 1.5 子协议号查询接口
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/querySubContractId")
	public String querySubContractId(HttpServletRequest request,Model model) throws Exception{
		String queryBalanceUrl = "http://180.166.114.155:58082/delegate-collect-front/subcontract/querySubContractIdJson";
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(request.getParameter("accountId"));
		sf.append("&name=").append(request.getParameter("name"));
		sf.append("&cardNo=").append(request.getParameter("cardNo"));
		sf.append("&idCardNo=").append(request.getParameter("idCardNo"));
		sf.append("&key=").append(key);
		
		String mac = Md5Encrypt.md5(sf.toString()).toUpperCase();
		
		JSONObject obj = new JSONObject();  
	 	obj.put("accountId",request.getParameter("accountId")); 
	 	obj.put("name",request.getParameter("name"));
	 	obj.put("cardNo",request.getParameter("cardNo")); 
	 	obj.put("idCardNo",request.getParameter("idCardNo"));
	 	obj.put("mac",mac); 
		
	 	String result = AppHttp.appadd(queryBalanceUrl,obj); 
 		System.out.println("result:"+result);
	 	model.addAttribute("msg", result);
	 	return "result";	
	 }
	/**
	 * 1.6 子协议延期接口
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/subConstractExtension")
	public String subConstractExtension(HttpServletRequest request,Model model) throws Exception{
		String queryBalanceUrl = "http://180.166.114.155:58082/delegate-collect-front/subcontract/subConstractExtensionJson";
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(request.getParameter("accountId"));
		sf.append("&contractId=").append(request.getParameter("contractId"));
		sf.append("&subContractId=").append(request.getParameter("subContractId"));
		sf.append("&startDate=").append(request.getParameter("startDate"));
		sf.append("&endDate=").append(request.getParameter("endDate"));
		sf.append("&key=").append(key);
		
		String mac = Md5Encrypt.md5(sf.toString()).toUpperCase();
		
		JSONObject obj = new JSONObject();  
	 	obj.put("accountId",request.getParameter("accountId")); 
	 	obj.put("contractId",request.getParameter("contractId"));
	 	obj.put("subContractId",request.getParameter("subContractId")); 
	 	obj.put("startDate",request.getParameter("startDate"));
	 	obj.put("endDate",request.getParameter("endDate"));
	 	obj.put("mac",mac); 
		
	 	String result = AppHttp.appadd(queryBalanceUrl,obj); 
 		System.out.println("result:"+result);
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
	 * 接受异步结果通知 form
	 * @param responseMode
	 * @throws Exception
	 */
	@RequestMapping(value="/callBackform")
	public @ResponseBody void responseCallBackForm(ResponseMode responseMode) throws Exception {
		System.out.println("结果通知>>>"+responseMode.toString());
		String accountId = "20130523134348001";
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
