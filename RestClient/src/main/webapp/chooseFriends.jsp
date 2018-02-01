<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
	<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% ArrayList<String> ans=(ArrayList<String>)request.getAttribute("listans"); %>
<form action="AddItemsRegController" method="post">
<h1>Give registry name below:</h1>
<p>
<input type="text" name="RegName" />
</p>
<br/>
<table>
	<tr>
		<td>
		Name
		</td>
		<td>
		Check it
		</td>
	</tr>
	<%
	for(int i=0;i<ans.size();i++)
	{
		%><tr>
			<%
					%><td><%=ans.get(i)%></td><%
					%><td><input type="checkbox" name="friends" value="<%=ans.get(i)%>"></td><%
					%>
			</tr><%		
	}
	%>
</table>
<input type="submit" name="add" value="Add Items to registry" class="btn btn-primary">
</form>


<form action="welcome_page.jsp" method="post">
<p>

</p>
<input type="submit" name="add" value="Go to Home" class="btn btn-primary">
</form>
</body>
</html>