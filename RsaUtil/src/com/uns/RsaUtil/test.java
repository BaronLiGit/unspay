package com.uns.RsaUtil;

public class test {
	public static void main(String[] args){
		/*Request dcRequest=new Request();
		accountId=request.getParameter("accountId");
		key=request.getParameter("key");
		String orderId=request.getParameter("orderId");
		String amount=request.getParameter("amount");
		String responseUrl=request.getParameter("responseUrl");
		String backResponseUrl=request.getParameter("backResponseUrl");
		
		String customerId=request.getParameter("customerId");
		String name=request.getParameter("name");
		String cardNo=request.getParameter("cardNo");
		String certNo=request.getParameter("certNo");
		*/
		String customerId = "";
		String name = "";
		String cardNo = "";
		String certNo = "";
		
		String accountId="1120180620132221001";
		String orderId="";
		String amount="";
		String responseUrl="";
		String backResponseUrl="";
		
		String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALWsClm1BQ8SUlBjeKQb3TqoLdOPKQKJYBGOXcImYYntZ39GHXKv0qoJf0s+/ZQfcrZILy6WBkjQdS3FFVqPFXuZNUIOjYBlwQUSTd5TeU+oPf388Qxsn3SMy5GCo7RY0rJgVcOS/caa/xgVYfh8x7EsF/fCsHy8oF5ZeAjmuAdBAgMBAAECgYBUye2uvpCU3y0xvashlKuk47c3pPoz59/BcduKcJVXZUjHmvbFfw/oMjSJ7uU0y5SjRH9Y08YtF7WI3hHASUVxt8nZ1/YERqxbMgRMeIyxBFU2884wvCTmLg+DqSBvVwxIT1sKyzc+MH9yV7V5bch34EfRtXj7nZBU9UYlFiHYEQJBAN1K4MtdJ/Jk83X71n1oEzCmAQ0Oe4rNDrHluoWsiBZ+q+unBIJycH1Cygywb3mTvJrHbH+IgCHquU0trSyjzS0CQQDSKlwomZL5Jniewp0s+b8XP+mlIHibiYvjAIqCkXH7X8oyqzrZMAmpjWDDhUJQBzB6pwKVt1ks8W67mT0M0jblAkEAkeEhrY5cniNEh5ub+xHXzLMycCwC7y5cqJOb0TE6iTG6Hd/9Tg0o8LpNve21nvzwUUxzzuLqLPGydBlPVuUohQJAEmuECErthsjIaaHFBNwe03rWj5J4/6jeidbSTP9SbiXHIwm+7qo3LpUllf1oAnue1CEvKNAZEMdYXTjhEO6bwQJBAItdaZmG3WmcG/HQUnR482D8OO14hUwLWFWsrf81E+2GdROKQvsjPR9m7AMlha48EsZBapz8TpxZGAfCnNJSzY8=";
		//String publicKey="";
		
		StringBuffer reaData=new StringBuffer(customerId);
		String data=reaData.append("|").append(name).append("|").append(cardNo).append("|").append(certNo).toString();
		System.out.println("RSA签名前数据data："+data);
		String sign=null;
		String baseData=null;
		try {
			//用私钥进行加密
			byte[] rsaData=RSAUtils.encryptByPrivateKey(data.getBytes(),privateKey);
			baseData=Base64Utils.encode(rsaData);
			System.out.println("RSA加密并Base64Util处理后的值："+baseData);
			
			StringBuffer signData=new StringBuffer(accountId);
			String signStr=signData.append("&").append(orderId).append("&").append(amount).append("&").append(responseUrl)
					.append("&").append(backResponseUrl).append("&").append(baseData).toString();
			System.out.println("组装数字签名的值："+signStr);
			//数字签名
			sign= RSAUtils.sign(signStr.getBytes(), privateKey);
			System.out.println("数字签名后的值："+sign);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
}
