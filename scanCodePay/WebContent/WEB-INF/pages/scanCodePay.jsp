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
<title>通道扫码支付</title>
</head>
<body>
<input type="button" onclick="window.location.href='<%=basePath+"forward?status=1" %>'" value="1.1 申请二维码"><br><br><br><br>
<input type="button" onclick="window.location.href='<%=basePath+"forward?status=2" %>'" value="1.2 订单状态查询接口"><br><br><br><br>
</body>
</html>