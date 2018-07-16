<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>子协议号查询接口</title>
</head>
<body>
<form action ="http://localhost:8080/delegateCollect/querySubContractId" method="post" target="_blank">
		<table width="600" border="0" cellspacing="5">
			<tr>
				<td>商户编号</td>
				<td ><input type="text" name="accountId" value=""></td>
			</tr>
			<tr>
				<td>银行卡号</td>
				<td><input type="text" name="cardNo" value=""></td>
			</tr>
			<tr>
				<td>用户姓名</td>
				<td ><input type="text" name="name" value=""></td>
			</tr>
			<tr>
				<td>身份证号</td>
				<td><input type="text" name="idCardNo" value=""></td>
			</tr>
			<tr>
				<td align="center"colspan="2"><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>