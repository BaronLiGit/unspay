<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="jquery.js" type="text/javascript"></script> 
<script language=javascript>
$(document).ready(function(){
	$("#asd").click(function(){
		alert($("#version").val());
	$.ajax({ 
		type: "get", 
		url : "/springMVC01/B2Cunspay/request.do",  
		data: 'version='+$("#version").val(), 
		success: function(json){
		alert(json.msg); 
		//$('#result').html("姓名:" + json.username + "<br/>密码:" + json.password); 
		} 
		}); 
});
});

</script> 
</head>
<body>
${msg}
<div>
	<form action="/springMVC01/B2CUnspay/pay" method="post">
		<table width="600" border="0" cellspacing="5">
			<tr>
				<td align="right">version：</td>
				<td align="left"><input type="text" id="version" name="version" value="" /></td>
			</tr>
			<tr>
				<td align="right">merchantId：</td>
				<td align="left"><input type="text" name="merchantId" value="${version}" /></td>
			</tr>
			<tr>
				<td align="right">merchantUrl：</td>
				<td align="left"><input type="text" name="merchantUrl" value="" /></td>
			</tr>
			<tr>
				<td align="right">responseMode：</td>
				<td align="left"><input type="text" name="responseMode" value="" /></td>
			</tr>
			<tr>
				<td align="right">orderId：</td>
				<td align="left"><input type="text" name="orderId" value="" /></td>
			</tr>
			<tr>
				<td align="right">currencyType：</td>
				<td align="left"><input type="text" name="currencyType" value="" /></td>
			</tr>
			<tr>
				<td align="right">amount：</td>
				<td align="left"><input type="text" name="amount" value="${msg}" /></td>
			</tr>
			<tr>
				<td align="right">assuredPay：</td>
				<td align="left"><input type="text" name="assuredPay" value="" /></td>
			</tr>
			<tr>
				<td align="right">remark：</td>
				<td align="left"><input type="text" name="remark" value="" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交订单" /></td>
			</tr>
		</table>
	</form>
	<input id="asd" name="asd" type="button" value="订单" />
</div>
</body>
</html>