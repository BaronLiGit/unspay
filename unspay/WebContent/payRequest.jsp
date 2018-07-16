<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>消费网银支付订单请求接口</title>
<script type="text/javascript" src="static/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="static/md5.js"></script>
<script type="text/javascript" src="static/b2c.js"></script>
</head>
<body>
	<form
		action="http://180.166.114.155/unspay/page/linkbank/payRequest.do"
		method="Post" id="unspay">
		<table width="600" border="0" cellspacing="5">
			<tr>
				<td align="right">version：</td>
				<td align="left"><input type="text" id="version" name="version"
					value="3.0.0" /></td>
			</tr>
			<tr>
				<td align="right">merchantId：</td>
				<td align="left"><input type="text" id="merchantId"
					name="merchantId" value="" /></td>
			</tr>
			<tr>
				<td align="right">merchantUrl：</td>
				<td align="left"><input type="text" id="merchantUrl"
					name="merchantUrl" value="" /></td>
			</tr>
			<tr>
				<td align="right">responseMode：</td>
				<td align="left"><input type="text" id="responseMode"
					name="responseMode" value="3" /></td>
			</tr>
			<tr>
				<td align="right">orderId：</td>
				<td align="left"><input type="text" id="orderId" name="orderId"
					value="" /></td>
			</tr>
			<tr>
				<td align="right">currencyType：</td>
				<td align="left"><input type="text" id="currencyType"
					name="currencyType" value="CNY" /></td>
			</tr>
			<tr>
				<td align="right">amount：</td>
				<td align="left"><input type="text" id="amount" name="amount"
					value="0.01" /></td>
			</tr>
			<tr>
				<td align="right">assuredPay：</td>
				<td align="left"><input type="text" id="assuredPay"
					name="assuredPay" value="false" /></td>
			</tr>
			<tr>
				<td align="right">remark：</td>
				<td align="left"><input type="text" id="remark" name="remark"
					value="test" /></td>
			</tr>
			<tr>
				<td><input type="hidden" id="time" name="time" /></td>
			</tr>
			<tr>
				<td align="right">frontURL：</td>
				<td align="left"><input type="text" id="frontURL"
					name="frontURL" value="" /></td>
			</tr>
			<tr>
				<td align="right">merchantKey</td>
				<td align="left"><input type="text" id="merchantKey"
					name="merchantKey" value="123456" /></td>
			</tr>
			<tr>
				<td align="right">MAC</td>
				<td><input type="text" id="mac" name="mac" /></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="button"
					onclick="mymd5();" value="计算签名"></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="确定"></td>
			</tr>
		</table>
	</form>
</body>
</html>