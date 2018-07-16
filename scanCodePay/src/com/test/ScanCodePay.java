package com.test;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.WriterException;

import net.sf.json.JSONObject;
import util.AppHttp;
import util.Md5Encrypt;
import util.QRCodeUtil;
@Controller
public class ScanCodePay {
	/**
	 * 二维码生成
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/QRCode")
   public void ScanCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
	   request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String website = request.getParameter("website");
		System.out.println(website);
		try {
			QRCodeUtil.genGR(website, response.getOutputStream());
		} catch (WriterException e) {
			e.printStackTrace();
		}
   }
	/**
	 * 申请二维码
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/applyCodePay")
	public String applyCodePay(HttpServletRequest request,Model model) throws IOException{
		String  myurl = "http://180.166.114.156:18082/scancode-pay-front/scanCodePay/applyScanCode";
		String accountId = request.getParameter("accountId");
		String payType = request.getParameter("payType");
		String orderId = request.getParameter("orderId");
		String commodity = request.getParameter("commodity");
		String amount = request.getParameter("amount");
		String responseUrl = request.getParameter("responseUrl");
		String ext = request.getParameter("ext");
		String key = "123456";
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(accountId);
		sf.append("&payType=").append(payType);
		sf.append("&orderId=").append(orderId);
		sf.append("&commodity=").append(commodity);
		sf.append("&amount=").append(amount);
		sf.append("&responseUrl=").append(responseUrl);
		sf.append("&key=").append(key);

		String mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
		
		JSONObject obj = new JSONObject();  
	 	obj.put("accountId",accountId); 
	 	obj.put("payType",payType);
	 	obj.put("orderId",orderId); 
	 	obj.put("commodity", commodity); 
	 	obj.put("amount",amount); 
	 	obj.put("responseUrl",responseUrl); 
	 	obj.put("ext",ext);
	 	obj.put("mac",mac); 
	 	String result = AppHttp.appadd(myurl,obj); 
	 	JSONObject jso = JSONObject.fromString(result);
	 	if("0000".equals((String) jso.get("result_code"))){
	 		String qrCodeString= (String) jso.get("qrcode");
			model.addAttribute("msg", qrCodeString);
			return "code";
	 	}else{
	 		String result_msg = (String) jso.get("result_msg");
	 		model.addAttribute("msg", result_msg);
	 		return "result";
	 	}
	 	
	}
	@RequestMapping(value="/queryOrderStatus")
	public String queryBalance(HttpServletRequest request,Model model) throws Exception{
		String queryBalanceUrl = "";
		String key = "Uns56888";
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
	@RequestMapping(value="/response")
	@ResponseBody
	public void responseCollect(@RequestBody Map<String, String> param) throws Exception{
		System.out.println("扫码支付结果通知:"+param);
		String accountId = "1120080226142547001";
		String key = "Uns56888";
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(accountId);
		sf.append("&orderId=").append(param.get("orderId"));
		sf.append("&amount=").append(param.get("amount"));
		sf.append("&result_code=").append(param.get("result_code"));
		sf.append("&result_msg=").append(param.get("result_msg"));
		sf.append("&key=").append(key);
		System.out.println("扫码支付结果通知mac串："+sf.toString());
		String responseMac=param.get("mac");
		System.out.println("响应mac:"+responseMac);
		String mac=Md5Encrypt.md5(sf.toString()).toUpperCase();
		System.out.println("生成mac:"+mac);
		if (mac.equals(responseMac)) {
			System.out.println("验签成功");
		}else {
			System.out.println("验签失败");
		}
	
	}
	@RequestMapping("/forward")
	public String forward(String status) throws Exception {
		if("1".equals(status)){
			return "applyScanCode";
		}else if ("2".equals(status)){
			return "queryOrderStatus";
		}
		return "result";
	}
}
