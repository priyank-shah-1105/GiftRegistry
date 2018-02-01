<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.List"%>
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
alert("Added to Registry Successfully");
} 
</script>
<script type="text/javascript">
function alertName1(){
alert("Item already exists");
} 

function redirect(elem){
    elem.setAttribute("action","welcome_page.jsp");
    elem.submit();
}
</script>  
<script src="ddtf.js" type="text/javascript"></script>
</head>
<body>
<% ArrayList<HashMap<String,String>> products=(ArrayList<HashMap<String,String>>)request.getAttribute("Product_details"); %>

<% ArrayList<HashMap<String,String>> ans=(ArrayList<HashMap<String,String>>)request.getAttribute("listans");

%>
<form action="SearchItemController" method="post">
<p>
<input type="text" name="query" />
</p>

<input type="submit" name="add" value="Search Items" class="btn btn-primary">

</form>
<br/>

<form action="AddRegistryController" method="post">
<br/>
<div class="container">
<table name="myTable" id="myTable" class="table table-bordered table-hover">
	<tr>
		<th>Product Name</th>
		<th>Product Description</th>
		<th>Product Price</th>
		<th>Category Name</th>
		<th>Brand Name</th>
		<th>Color Name</th>	
		<th> </th>		
	</tr>	
	<%
	int reg_id=Integer.parseInt(request.getAttribute("reg_id").toString());
	for(int i=0;i<products.size();i++)
		{
		HashMap<String,String>hm= products.get(i);		
			%><tr>
			<%
					%><td><%=hm.get("product_name") %></td><%
					%><td><%=hm.get("product_desc") %></td><%
					%><td><%=hm.get("product_price") %></td><%
					%><td><%=hm.get("category_name") %></td><%
					%><td><%=hm.get("brand_name") %></td><%
					%><td><%=hm.get("color_name") %></td><%		
					%><td>
					
					<a href="AddRegistryController?reg_id=<%=reg_id%>&product_id=<%=hm.get("product_id")%>">
					<input type="button" name="add<%=i %>" value="add to registry" class="btn btn-primary">
					</a>
					</td><%		
				%>
			</tr><% 
		}
		
		%>
</table>
</div>
<script>$('#myTable').ddTableFilter();</script>
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