<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>委托支付支付接口</title>
</head>
	<form action ="http://localhost:8080/delegateCollect/delegateCollect" method="post" target="_blank">
		<table width="600" border="0" cellspacing="5">
			<tr>
				<td>商户编号</td>
				<td ><input type="text" name="accountId" value=""></td>
			</tr>
			<tr>
				<td>子协议编号</td>
				<td><input type="text" name="subContractId" value=""></td>
			</tr>
			<tr>
				<td>订单号</td>
				<td><input type="text" name="orderId" value=""></td>
			</tr>
			<tr>
				<td>扣款目的</td>
				<td><input type="text" name="purpose" value=""></td>
			</tr>
			<tr>
				<td>金额</td>
				<td><input type="text" name="amount" value=""></td>
			</tr>
			<tr>
				<td>手机号</td>
				<td><input type="text" name="phoneNo" value=""></td>
			</tr>
			<tr>
				<td>响应地址</td>
				<td><input type="text" name="responseUrl" value=""></td>
			</tr>
				<tr><td align="center"colspan="2"><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
<body>
</body>
</html>