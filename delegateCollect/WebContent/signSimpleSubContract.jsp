<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>子协议录入</title>
</head>
<body>
<form action ="http://localhost:8080/delegateCollect/signSimpleSubContract" method="post" target="_blank">
<table width="600" border="0" cellspacing="5">
			<tr>
				<td>银行卡号</td>
				<td ><input type="text" name="cardNo" value="6214920602853499"></td>
			</tr>
			<tr>
				<td>用户姓名</td>
				<td><input type="text" name="name" value="李清泉"></td>
			</tr>
			<tr>
				<td>身份证号</td>
				<td><input type="text" name="idCardNo" value="420528199210163514"></td>
			</tr>
			<tr>
				<td>手机号</td>
				<td><input type="text" name="phoneNo" value="13687228231"></td>
			</tr>
			<tr>
				<td>子协议开始时间</td>
				<td><input type="text" name="startDate" value=""></td>
			</tr>
			<tr>
				<td>子协议结束时间</td>
				<td><input type="text" name="endDate" value=""></td>
			</tr>
			<tr>
				<td>商户协议编号</td>
				<td><input type="text" name="contractId" value="1120130523134348001"></td>
			</tr>
			<tr>
				<td>商户编号</td>
				<td><input type="text" name="accountId" value="1120130523134348001"></td>
			</tr>
				<tr><td align="center"colspan="2"><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>