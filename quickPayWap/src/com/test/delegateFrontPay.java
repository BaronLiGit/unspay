package com.test;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.util.HttpFormParam;
import com.util.Md5Encrypt;
import com.mode.ResponseMode;
@Controller

public class delegateFrontPay {
	String key = "123456" ;
	String accountId = "";
	
	/**
	 * 订单状态查询
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryOrderStatus")
	public String queryOrderStatus(HttpServletRequest request,Model model) throws Exception{
		String myurl = "http://180.166.114.155:18083/quickpay-front/quickPayWap/queryOrderStatus";
		accountId = request.getParameter("accountId");
		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(request.getParameter("accountId"));
		sf.append("&orderNo=").append(request.getParameter("orderId"));
		sf.append("&key=").append(key);
		String mac =Md5Encrypt.md5(sf.toString()).toUpperCase();
		HashMap<String,String> param = new HashMap<String,String>();
		param.put("accountId", accountId);
		param.put("orderNo", request.getParameter("orderId"));
		param.put("mac", mac);
		String result = HttpFormParam.doPost(myurl, param);
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
		sf.append("accountId=").append(responseMode.getAccountId());
		sf.append("&orderNo=").append(responseMode.getOrderNo());
		sf.append("&userId=").append(responseMode.getUserId());
		sf.append("&bankName=").append(responseMode.getBankName());
		sf.append("&tailNo=").append(responseMode.getTailNo());
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
			return "quickPayWap";
		}else if ("2".equals(status)){
			return "queryOrderStatus";
		}else if ("3".equals(status)){
			return "queryOrderStatus";
		}else{
			return "result";
		}
		
	}
	
}

