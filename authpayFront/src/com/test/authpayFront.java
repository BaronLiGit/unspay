package com.test;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.util.AppHttp;
import com.util.Md5Encrypt;
import com.util.ResponseMode;

import net.sf.json.JSONObject;
@Controller
public class authpayFront {
	//商户密钥
	String key = "123456abc";
	//商户号
	String accountId = "";
	/**
	 * 认证支付(接口)首次 & 再次  支付
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/pay")
	public String authPay(HttpServletRequest request,Model model) throws Exception{
		//请求地址  (请求地址 和 商户信息 对应 分测试和正式)
		//预支成功后 会向phoneNo对应的手机发送一次验证码
		String myUrl = "http://180.166.114.155:28082/authPay-front/authPay/pay";
		if("0".equals(request.getParameter("payType"))){
			System.out.println("预支付接口（首次）");
			StringBuffer sf = new StringBuffer();
			sf.append("accountId=").append(request.getParameter("accountId"));
			sf.append("&customerId=").append(request.getParameter("customerId"));
			sf.append("&payType=").append(request.getParameter("payType"));
			sf.append("&name=").append(request.getParameter("name"));
			sf.append("&phoneNo=").append(request.getParameter("phoneNo"));
			sf.append("&cardNo=").append(request.getParameter("cardNo"));
			sf.append("&idCardNo=").append(request.getParameter("idCardNo"));
			sf.append("&orderId=").append(request.getParameter("orderId"));
			sf.append("&purpose=").append(request.getParameter("purpose"));
			sf.append("&amount=").append(request.getParameter("amount"));
			if(request.getParameter("responseUrl")!=""&& request.getParameter("responseUrl")!=null){
				sf.append("&responseUrl=").append(request.getParameter("responseUrl"));
			}
			sf.append("&key=").append(key);
			//MD5计算
			String mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
			System.out.println("mac字符串:"+sf.toString());
			System.out.println("mac加密结果:"+mac);
			//json 数据
			JSONObject obj = new JSONObject();
			obj.put("accountId", request.getParameter("accountId"));
			obj.put("customerId", request.getParameter("customerId"));
			obj.put("payType", request.getParameter("payType"));
			obj.put("name", request.getParameter("name"));
			obj.put("phoneNo", request.getParameter("phoneNo"));
			obj.put("cardNo", request.getParameter("cardNo"));
			obj.put("idCardNo", request.getParameter("idCardNo"));
			obj.put("orderId", request.getParameter("orderId"));
			obj.put("purpose", request.getParameter("purpose"));
			obj.put("amount", request.getParameter("amount"));
			obj.put("responseUrl", request.getParameter("responseUrl"));
			obj.put("mac", mac);
			System.out.println("请求参数:"+obj);
			//发送请求
			String result = AppHttp.appadd(myUrl,obj); 
	 		System.out.println("result:"+result);
		 	model.addAttribute("msg", result);
			
		}else if("1".equals(request.getParameter("payType"))){
			System.out.println("预支付接口（再次）");
			StringBuffer sf = new StringBuffer();
			sf.append("accountId=").append(request.getParameter("accountId"));
			sf.append("&customerId=").append(request.getParameter("customerId"));
			sf.append("&payType=").append(request.getParameter("payType"));
			sf.append("&token=").append(request.getParameter("token"));
			sf.append("&orderId=").append(request.getParameter("orderId"));
			sf.append("&purpose=").append(request.getParameter("purpose"));
			sf.append("&amount=").append(request.getParameter("amount"));
			if(request.getParameter("responseUrl")!=""&& request.getParameter("responseUrl")!=null){
				sf.append("&responseUrl=").append(request.getParameter("responseUrl"));
			}
			
			sf.append("&key=").append(key);
			//MD5计算
			String mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
			System.out.println("mac字符串:"+sf.toString());
			System.out.println("mac加密结果:"+mac);
			//json 数据
			JSONObject obj = new JSONObject();
			obj.put("accountId", request.getParameter("accountId"));
			obj.put("customerId", request.getParameter("customerId"));
			obj.put("payType", request.getParameter("payType"));
			obj.put("orderId", request.getParameter("orderId"));
			obj.put("purpose", request.getParameter("purpose"));
			obj.put("amount", request.getParameter("amount"));
			obj.put("responseUrl", request.getParameter("responseUrl"));
			obj.put("token", request.getParameter("token"));
			obj.put("mac", mac);
			System.out.println("请求参数:"+obj);
			//发送请求
			String result = AppHttp.appadd(myUrl,obj); 
	 		System.out.println("result:"+result);
		 	model.addAttribute("msg", result);
		}else{
			System.out.println("payType不正确!");
		}
		return "result";
	}
	/**
	 * 重发短信验证码  当调用预支付 不能收到验证码 1分钟后 调用 短信验证码重发
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/reSendVercode")
	public String reSendVercode(HttpServletRequest request,Model model) throws Exception {
		System.out.println("重发短信验证码");
		String myUrl = "http://180.166.114.155:28082/authPay-front/authPay/sendVercode";
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(request.getParameter("accountId"));
		sf.append("&customerId=").append(request.getParameter("customerId"));
		sf.append("&token=").append(request.getParameter("token"));
		sf.append("&orderId=").append(request.getParameter("orderId"));
		sf.append("&phoneNo=").append(request.getParameter("phoneNo"));
		sf.append("&key=").append(key);
		//MD5计算
		String mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
		System.out.println("mac字符串:"+sf.toString());
		System.out.println("mac加密结果:"+mac);
		//json 数据
		JSONObject obj = new JSONObject();
		obj.put("accountId", request.getParameter("accountId"));
		obj.put("customerId", request.getParameter("customerId"));
		obj.put("orderId", request.getParameter("orderId"));
		obj.put("phoneNo", request.getParameter("phoneNo"));
		obj.put("token", request.getParameter("token"));
		obj.put("mac", mac);
		System.out.println("请求参数:"+obj);
		//发送请求
		String result = AppHttp.appadd(myUrl,obj); 
 		System.out.println("result:"+result);
	 	model.addAttribute("msg", result);
		return "result";
	}
	/**
	 * 确认支付
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/confirm")
	public String confirm(HttpServletRequest request,Model model) throws Exception {
		System.out.println("确认支付");
		String myUrl = "http://180.166.114.155:28082/authPay-front/authPay/confirm";
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(request.getParameter("accountId"));
		sf.append("&customerId=").append(request.getParameter("customerId"));
		sf.append("&token=").append(request.getParameter("token"));
		sf.append("&orderId=").append(request.getParameter("orderId"));
		sf.append("&vericode=").append(request.getParameter("vericode"));
		sf.append("&key=").append(key);
		//MD5计算
		String mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
		System.out.println("mac字符串:"+sf.toString());
		System.out.println("mac加密结果:"+mac);
		//json 数据
		JSONObject obj = new JSONObject();
		obj.put("accountId", request.getParameter("accountId"));
		obj.put("customerId", request.getParameter("customerId"));
		obj.put("orderId", request.getParameter("orderId"));
		obj.put("vericode", request.getParameter("vericode"));
		obj.put("token", request.getParameter("token"));
		obj.put("mac", mac);
		System.out.println("请求参数:"+obj);
		//发送请求
		String result = AppHttp.appadd(myUrl,obj); 
 		System.out.println("result:"+result);
	 	model.addAttribute("msg", result);
		return "result";
	}
	/**
	 * 订单查询
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/queryOrder")
	public String queryOrder(HttpServletRequest request,Model model) throws Exception {
		System.out.println("订单查询");
		String myUrl = "http://180.166.114.155:28082/authPay-front/authPay/queryOrderStatus";
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(request.getParameter("accountId"));
		sf.append("&orderId=").append(request.getParameter("orderId"));
		sf.append("&key=").append(key);
		//MD5计算
		String mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
		System.out.println("mac字符串:"+sf.toString());
		System.out.println("mac加密结果:"+mac);
		//json 数据
		JSONObject obj = new JSONObject();
		obj.put("accountId", request.getParameter("accountId"));
		obj.put("orderId", request.getParameter("orderId"));
		obj.put("mac", mac);
		System.out.println("请求参数:"+obj);
		//发送请求
		String result = AppHttp.appadd(myUrl,obj); 
 		System.out.println("result:"+result);
	 	model.addAttribute("msg", result);
		return "result";
	}
	/**
	 * 解绑
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/unbind")
	public String unbind(HttpServletRequest request,Model model) throws Exception {
		System.out.println("解绑");
		String myUrl = "http://180.166.114.155:28082/authPay-front/authPay/unbind";
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(request.getParameter("accountId"));
		sf.append("&customerId=").append(request.getParameter("customerId"));
		sf.append("&token=").append(request.getParameter("token"));
		sf.append("&key=").append(key);
		//MD5计算
		String mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
		System.out.println("mac字符串:"+sf.toString());
		System.out.println("mac加密结果:"+mac);
		//json 数据
		JSONObject obj = new JSONObject();
		obj.put("accountId", request.getParameter("accountId"));
		obj.put("customerId", request.getParameter("customerId"));
		obj.put("token", request.getParameter("token"));
		obj.put("mac", mac);
		System.out.println("请求参数:"+obj);
		//发送请求
		String result = AppHttp.appadd(myUrl,obj); 
 		System.out.println("result:"+result);
	 	model.addAttribute("msg", result);
		return "result";
	}
	/**
	 * 接受异步结果通知 form
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
	@RequestMapping(value="/forward")
	public String forward(String status) throws Exception {
		if("1".equals(status)){
			return "authpay";
		}else if ("2".equals(status)){
			return "confirm";
		}else if ("3".equals(status)){
			return "reauthpay";
		}else if ("4".equals(status)){
			return "revericode";
		}else if ("5".equals(status)){
			return "queryorders";
		}else if ("6".equals(status)){
			return "unbind";
		}else{
			return "result";
		}
		
	}
}
