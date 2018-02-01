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
<script type="text/javascript">
function alertName(){
alert("Item deleted from registry successfully");
} 
</script>
<script type="text/javascript">
function alertName1(){
alert("Item was not able to delete by some reason");
} 
</script>
</head>
<body>
<h1 style="color:blue">Shared Registry With You</h1>
<% ArrayList<HashMap<String,String>> ans=(ArrayList<HashMap<String,String>>)request.getAttribute("listans"); %>
<form action="AddRegistryController" method="post">
<div class="container">
<table class="table table-bordered table-hover">
	<tr>
		<td>Friend Name</td>
		<td>Registry Name</td>
		
		<td>Product Name</td>
		<td>Product Description</td>
		<td>Product Price</td>
		<td>Category Name</td>
		<td>Brand Name</td>
		<td>Color Name</td>		
		<td>Assign To</td>		
		
	</tr>	
	<%	for(int i=0;i<ans.size();i++)
		{
		HashMap<String,String>hm= ans.get(i);		
			%><tr>
			<%
			%><td><%=hm.get("friend") %></td><%
			%><td><%=hm.get("reg_name") %></td><%
		
					%><td><%=hm.get("product_name") %></td><%
					%><td><%=hm.get("product_desc") %></td><%
					%><td><%=hm.get("product_price") %></td><%
					%><td><%=hm.get("category_name") %></td><%
					%><td><%=hm.get("brandname") %></td><%
					%><td><%=hm.get("colorname") %></td><%		
					
					%>
					
					<%if(hm.get("assignTo").equals("1")){ %><td>
					<a href="SelfAssign?productid=<%=hm.get("productid")%>&friend=<%=hm.get("friend")%>&regname=<%=hm.get("reg_name")%>">
					<input type="button" name="add<%=i %>" value="self Assign" class="btn btn-primary">
					</a></td>
				
					<%-- <form action="SelfAssign" method="get">
	<button class="btn btn-lg btn-primary btn-block" type="submit" value="Profile">Self Assign</button>
		<input type=hidden name="product_id" value="<%=hm.get("product_id")%>">
		<input type=hidden name="friend" value="<%=hm.get("friend")%>">
		<input type=hidden name="reg_name" value="<%=hm.get("reg_name")%>">
		
		</form>	 --%>
					
					<%	}else{%><%
					%><td><%=hm.get("assignTo") %></td><%	
					}%>	
				
			</tr><% 
		}
		
		%>
</table>
</div>
</form>
<%  if(request.getAttribute("success")!=null)
	{%>
		<script type="text/javascript"> window.onload = alertName; </script>
	<% }%>
<%  if(request.getAttribute("failure")!=null)
	{%>
		<script type="text/javascript"> window.onload = alertName1; </script>
	<% }%>

		<form action="welcome_page.jsp" method="post">
<p>

</p>
<input type="submit" name="add" value="Go to Home" class="btn btn-primary">
</form>
</body>
</html>