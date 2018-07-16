package com.test;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/demoTest")
public class responseDemo {
	private String key="";
	private String accountId="";
	/**
	 * 结果通知
	 * @param dcCallBack
	 * @throws Exception 
	 */
	@RequestMapping(value="/action")
	@ResponseBody
	public void responseCollect(@RequestBody Map<String, String> param) throws Exception{
		System.out.println("结果通知:"+param);

		StringBuffer sf = new StringBuffer();
		sf.append("accountId=").append(accountId);
		sf.append("&orderId=").append(param.get("orderId"));
		sf.append("&amount=").append(param.get("amount"));
		sf.append("&result_code=").append(param.get("result_code"));
		sf.append("&result_msg=").append(param.get("result_msg"));
		sf.append("&key=").append(key);
		System.out.println("公众号支付结果通知mac串："+sf.toString());
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
}
