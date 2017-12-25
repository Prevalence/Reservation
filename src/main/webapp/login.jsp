<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login Here</title>
</head>
<body>
	<h2>登陆</h2>
	<form name="form1" method="post" action="LoginServlet">
		用户名：<input name="userName" type="text" id="username"
			style="width: 120px"><br> <br> 密码：<input
			name="password" type="text" id="password" style="width: 120px"><br>
		<br> <input type="submit" name="Submit" value="提交">
	</form>
	<%
		out.println("总人数为：" + application.getAttribute("totalNumberOfVisitor") + "   游客人数为："
				+ application.getAttribute("not logged in") + "   已登录人数为：" + application.getAttribute("logged in"));
	%>
</body>
</html>