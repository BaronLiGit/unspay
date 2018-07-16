<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String name =request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>认证支付(接口)</title>
</head>
<body>
<input type="button" onclick="window.location.href='<%=name+"/forward?status=1" %>'" value="预支付首次"><br><br><br><br>
<input type="button" onclick="window.location.href='<%=name+"/forward?status=2" %>'" value="确认支付"><br><br><br><br>
<input type="button" onclick="window.location.href='<%=name+"/forward?status=3" %>'" value="确认支付再次"><br><br><br><br>
<input type="button" onclick="window.location.href='<%=name+"/forward?status=4" %>'" value="短信重发"><br><br><br><br>
<input type="button" onclick="window.location.href='<%=name+"/forward?status=5" %>'" value="订单状态查询"><br><br><br><br>
<input type="button" onclick="window.location.href='<%=name+"/forward?status=6" %>'" value="解绑"><br><br><br><br>
</body>
</html>