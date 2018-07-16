<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>协议支付接口</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/macmd5.js"></script>
<script type="text/javascript" src="js/b2c.js"></script>
</head>
<body>
<form
	action="http://waptest.unspay.com:18083/quickpay-front/quickPayWapNucc/prePay"
	method="post" id="quickpay-front">
	<table width="600" border="0" cellspacing="5">
		<tr>
			<td>商户编号</td>
			<td><input type="text" id="accountId" name="accountId" value=""></td>
		</tr>
		<tr>
			<td>用户id</td>
			<td><input type="text" id="customerId" name="customerId"
				value=""></td>
		</tr>
		<tr>
			<td>订单号</td>
			<td><input type="text" id="orderNo" name="orderNo" value=""></td>
		</tr>
		<tr>
			<td>目的</td>
			<td><input type="text" id="purpose" name="purpose" value=""></td>
		</tr>
		<tr>
			<td>金额</td>
			<td><input type="text" id="amount" name="amount" value=""></td>
		</tr>
		<tr>
			<td>商品简称</td>
			<td><input type="text" id="commodityName" name="commodityName"
				value=""></td>
		</tr>
		<tr>
			<td>业务种类</td>
			<td><input type="text" id="businessType" name="businessType"
				value=""></td>
		</tr>
		<tr>
			<td>后台通知地址</td>
			<td><input type="text" id="responseUrl" name="responseUrl"
				value=""></td>
		</tr>
		<tr>
			<td>前台响应地址</td>
			<td><input type="text" id="pageResponseUrl"
				name="pageResponseUrl" value=""></td>
		</tr>
		<tr>
			<td>key</td>
			<td><input type="text" id="key" name="key" value=""></td>
		</tr>
		<tr>
			<td>mac</td>
			<td><input type="text" id="mac" name="mac" value=""></td>
		</tr>
		<tr>
			<td align="center" colspan="2"><input type="button"
				onclick="mymd5();" value="计算签名"></td>
		</tr>
		<tr>
			<td align="center" colspan="2"><input type="submit" value="提交"></td>
		</tr>
	</table>
	</form>
</body>
</html>