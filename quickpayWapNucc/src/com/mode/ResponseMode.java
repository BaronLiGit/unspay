package com.mode;

public class ResponseMode {
	private String result_code;
	private String result_msg;
	private String orderNo;
	private String userId;
	private String bankName;
	private String tailNo;
	private String amount;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getTailNo() {
		return tailNo;
	}
	public void setTailNo(String tailNo) {
		this.tailNo = tailNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	@Override
	public String toString() {
		return "ResponseMode [result_code=" + result_code + ", result_msg=" + result_msg + ", orderNo=" + orderNo
				+ ", userId=" + userId + ", bankName=" + bankName + ", tailNo=" + tailNo + ", amount=" + amount
				+ ", mac=" + mac + "]";
	}
}
	
	