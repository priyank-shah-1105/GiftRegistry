<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String a=(String)request.getAttribute("message");
if(a!=null){
	out.println(request.getAttribute("message"));
} %>
	
	<form action="LoginController" method="post">
		Name:<input type="text" name="name"><br>
		Password:<input type="password" name="password"><br>
		<input type="submit" value="login" class="btn btn-primary">
	</form>
	<a href="forgot.jsp">Forgot Password ?</a><br/>
</body>
</html>