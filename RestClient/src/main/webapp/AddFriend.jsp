<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
	<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


</head>
<body>
<% ArrayList<HashMap<String,String>> ans=(ArrayList<HashMap<String,String>>)request.getAttribute("listans"); %>
<form action="" method="post">
<div class="container">
<table class="table table-bordered table-hover">
	<tr>
		<td>First Name</td>
		<td>Last Name</td>
		<td>Email Id</td>
					
	</tr>	
	<%	for(int i=0;i<ans.size();i++)
		{
		HashMap<String,String>hm= ans.get(i);		
			%><tr>
			<%
					%><td><%=hm.get("first_name") %></td><%
					%><td><%=hm.get("last_name") %></td><%
					%><td><%=hm.get("email") %></td>					
					<td>
					<a href="AddFriendb?username=<%=hm.get("username")%>">
					Add Friend
					</a>
					</td><%		
				%>
			</tr><% 
		}
		
		%>
</table>
</form>
<form action="welcome_page.jsp" method="post">
<p>

</p>
<input type="submit" name="add" value="Go to Home" class="btn btn-primary">
</form>

</body>
</html>