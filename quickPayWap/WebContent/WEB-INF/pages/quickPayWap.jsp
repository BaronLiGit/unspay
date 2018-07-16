<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
   String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="lib/jquery.js"></script>
<script type="text/javascript" src="lib/md5.js"></script>
<script type="text/javascript">
 function sdf(){
	var mac="accountId="+$("#accountId").val()+"&"
	+"customerId="+$("#customerId").val()+"&"
	+"orderNo="+$("#orderNo").val()+"&"
	+"commodityName="+$("#commodityName").val()+"&"
	+"amount="+$("#amount").val()+"&"
	+"responseUrl="+$("#responseUrl").val()+"&"
	+"pageResponseUrl="+$("#pageResponseUrl").val()+"&"
	+"key="+$("#key").val()
	mac=md5(mac).toUpperCase()
	$("#mac").val(mac);
 }


</script>
<title>快捷支付接口</title>
</head>
	<form action ="http://quickpay.unspay.com/quickpay-front/quickPayWap/prePay" method="post" target="_blank">
		<table width="600" border="0" cellspacing="5">
			<tr>
				<td>商户编号</td>
				<td ><input type="text" name="accountId" id="accountId" value=""></td>
			</tr>
			<tr>
				<td>用户 ID</td>
				<td><input type="text" name="customerId" id="customerId" value=""></td>
			</tr>
			<tr>
				<td>订单号</td>
				<td><input type="text" name="orderNo" id="orderNo" value=""></td>
			</tr>
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="commodityName" id="commodityName" value=""></td>
			</tr>
			<tr>
				<td>金额 </td>
				<td><input type="text" name="amount" id="amount" value=""></td>
			</tr>
			<tr>
				<td>后台通知地址</td>
				<td><input type="text" name="responseUrl" id="responseUrl" value=""></td>
			</tr>
			<tr>
				<td>前台响应地址</td>
				<td><input type="text" name="pageResponseUrl" id="pageResponseUrl" value=""></td>
			</tr>
			<tr>
				<td>key</td>
				<td><input type="text" name="key" id="key" value="123456"></td>
			</tr>
			<tr>
				<td>mac</td>
				<td><input type="text" name="mac" id="mac" value=""></td>
			</tr>
			<tr>
				<td><input type="button" onclick="sdf()" value='md5测试' /></td>
				<td align="center"colspan="2"><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
<body>
</body>
</html>