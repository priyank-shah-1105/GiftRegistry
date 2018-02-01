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
<h1>Set New Password</h1>
	<form action="ResetController" method="post">
		New Password:<input type="password" name="newPassword"><br>
		Confirm Password:<input type="password" name="confirmPassword"><br>
		<input type=hidden name="name" value="<%=request.getAttribute("name")%>">
		
		<input type="submit" value="Change Password" class="btn btn-primary">
	</form>
	
</body>
</html>