<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>二维码申请接口</title>
</head>
<body>
<form action ="<%=basePath%>applyCodePay" method="post" target="_blank">
		<table width="600" border="0" cellspacing="5">
			<tr>
				<td>商户编号</td>
				<td ><input type="text" name="accountId" value="2120160922184756001"></td>
			</tr>
			<tr>
				<td>支付类型</td>
				<td><input type="text" name="payType" value="1"></td>
			</tr>
			<tr>
				<td>订单号</td>
				<td><input type="text" name="orderId" value=""></td>
			</tr>
			<tr>
				<td>商品信息</td>
				<td><input type="text" name="commodity" value=""></td>
			</tr>
			<tr>
				<td>金额</td>
				<td><input type="text" name="amount" value="0.01"></td>
			</tr>
			<tr>
				<td>响应地址</td>
				<td><input type="text" name="responseUrl" value=""></td>
			</tr>
			<tr>
				<td>备用字段</td>
				<td><input type="text" name="ext" value="测试"></td>
			</tr>
				<tr><td align="center"colspan="2"><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>