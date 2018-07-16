package com.util;

public class ResponseMode {
	private String result_code;
	private String result_msg;
	private String amount;
	private String orderId;
	private String mac;
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getResult_msg() {
		return result_msg;
	}
	public void setResult_msg(String result_msg) {
		this.result_msg = result_msg;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	 @Override
	    public String toString() {
	        return "ResponseMode [result_code=" + result_code + ", result_msg=" + result_msg + ", amount="
	                + amount + ",orderId=" + orderId + ",mac=" + mac + "]";
	    }
}
