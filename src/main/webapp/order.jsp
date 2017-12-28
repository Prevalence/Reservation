<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Orders</title>
</head>
<body>
	<table border=1 width=800px align=center>
		<tr align=center>
			<th>订单ID</th>
			<th>用户名</th>
			<th>交易时间</th>
			<th>货物</th>
			<th>单价</th>
			<th>数量</th>
			<th>总价</th>

		</tr>
		<c:forEach var="order" items="${sessionScope.orderList}">
			<tr>
				<th>${order.ID}</th>
				<td>${order.userName}</td>
				<td>${order.time}</td>
				<td>${order.item}</td>
				<td>${order.unitPrice}</td>
				<td>${order.number}</td>
				<td>${order.totalPrice}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<p>总在线人数: ${applicationScope.totalNumberOfVisitor} 游客人数：
		${applicationScope.tourist} 已登录人数： ${applicationScope.loggedIn}</p>
	<a href="LogOutServlet">Log Out</a>
</body>
</html>