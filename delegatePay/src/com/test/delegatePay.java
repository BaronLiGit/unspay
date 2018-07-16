package com.test;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.test.Md5Encrypt;
import com.test.HttpFormParam;
@Controller

public class delegatePay {
	public static final Logger log = LoggerFactory.getLogger(delegatePay.class);
	/**
	 * 实时代付订单支付接口
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/pay")
	public String responseCollect(HttpServletRequest request,Model model) throws Exception{
		log.info("实时代付接口"); 
		//组装验签参数
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(getProperty("accountId"));
		sf.append("&name=").append(request.getParameter("name"));
		sf.append("&cardNo=").append(request.getParameter("cardNo"));
		sf.append("&orderId=").append(request.getParameter("orderId"));
		sf.append("&purpose=").append(request.getParameter("purpose"));
		sf.append("&amount=").append(request.getParameter("amount"));
		if(getProperty("responseUrl")!=""&& getProperty("responseUrl")!=null){
			sf.append("&responseUrl=").append(getProperty("responseUrl"));
		}
		sf.append("&key=").append(getProperty("key"));
		//MD5 计算
		String mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
		log.info("mac验签串:"+sf.toString());  
		log.info("mac加密结果:"+mac);  
		// 请求参数
		 HashMap<String, String> param = new HashMap<String, String>();  
	        param.put("accountId", getProperty("accountId")); 
	        param.put("name", request.getParameter("name")); 
	        param.put("cardNo", request.getParameter("cardNo")); 
	        param.put("orderId", request.getParameter("orderId")); 
	        param.put("purpose", request.getParameter("purpose")); 
	        param.put("amount", request.getParameter("amount"));
	        param.put("responseUrl", getProperty("responseUrl")); 
	        param.put("mac", mac);
	       //发送请求
		 	String result = HttpFormParam.doPost(getProperty("pay"), param);
		 	log.info("代付请求参数:"+param);  
		 	log.info("代付请求结果:"+result);  
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
	/*
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
	/*
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
		log.info("实时代付交易结果 "+responseMode.toString());
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(getProperty("accountId"));
		sf.append("&orderId=").append(responseMode.getOrderId());
		sf.append("&amount=").append(responseMode.getAmount());
		sf.append("&result_code=").append(responseMode.getResult_code());
		sf.append("&result_msg=").append(responseMode.getResult_msg());
		sf.append("&key=").append(getProperty("key"));
		
		log.info("验签串>>>："+sf.toString());
		String responseMac=responseMode.getMac();
		log.info("接收到的MAC>>>"+responseMac);
		String mac=Md5Encrypt.md5(sf.toString()).toUpperCase();
		log.info("加密MAC>>>"+mac);
		if (responseMac.equals(mac)) {
			log.info("验签成功");
		}else {
			log.info("验签失败");
		}
	}
	/**
	 * 获取配置信息
	 * @param key
	 * @return value
	 */
	public String getProperty(String key){
		Properties props = new Properties();
		try {
			props.load(delegatePay.class.getClassLoader().getResourceAsStream("../delegatePay.properties"));
			String value =props.getProperty(key); 
			return value;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}  
	}
}
