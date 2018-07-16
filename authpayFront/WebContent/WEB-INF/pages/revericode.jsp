<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
   String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>短信验证码重发</title>
</head>
<body>
<form action =<%=path+"reSendVercode" %> method="post" target="_blank">
		<table width="600" border="0" cellspacing="5">
			<tr>
				<td>商户编号</td>
				<td ><input type="text" name="accountId" value=""></td>
			</tr>
			<tr>
				<td>用户编号</td>
				<td><input type="text" name="customerId" value=""></td>
			</tr>
			<tr>
				<td>订单号</td>
				<td><input type="text" name="orderId" value=""></td>
			</tr>
			<tr>
				<td>手机号</td>
				<td><input type="text" name="phoneNo" value=""></td>
			</tr>
			<tr>
				<td>授权码</td>
				<td><input type="text" name="token" value=""></td>
			</tr>
			 <tr>
			 	<td align="center"colspan="2"><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>