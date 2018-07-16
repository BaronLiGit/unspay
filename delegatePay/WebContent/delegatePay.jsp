<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
 
<%
	String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	String timeTag = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	String orderId = "ysb"+timeTag;
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>实时代付支付接口</title>
</head>
	<form action =<%=path+"pay" %> method="post" target="_blank">
		<table width="600" border="0" cellspacing="5">
			<tr>
				<td>姓名</td>
				<td><input type="text" name="name" value="李清泉"></td>
			</tr>
			<tr>
				<td>银行卡号</td>
				<td><input type="text" name="cardNo" value="6214920602853499"></td>
			</tr>
			<tr>
				<td>订单号</td>
				<td><input type="text" name="orderId" value=<%=orderId %>></td>
			</tr>
			<tr>
				<td>付款目的</td>
				<td><input type="text" name="purpose" value="代付测试"></td>
			</tr>
			<tr>
				<td>金额</td>
				<td><input type="text" name="amount" value="0.01"></td>
			</tr>
			<tr>
				<td align="center"colspan="2"><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
<body>
</body>
</html>