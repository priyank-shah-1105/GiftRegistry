<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.example.Login.LoginBean"%>
    <%@page import="com.example.Register.RegisterBean"%>
    <%@page import="java.util.List"%>
    <%@page import="java.util.ArrayList"%>
	<%@page import="java.util.HashMap"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="application/json">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="application/json">
<title>Insert title here</title>
<script type="text/javascript">
function alertName(){
alert("Added to cart Successfully");
} 
</script>
<script type="text/javascript">
function alertName1(){
alert("Item already exists");
} 

function redirect(elem){
    elem.setAttribute("action","AdminHome.jsp");
    elem.submit();
}
</script>  
</head>
<body>
<% 

	/*if (session == null)
  	{
    	String address = "/login-error.jsp";
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
    	dispatcher.forward(request,response);
  	}
	else
	{
		String name=(String)session.getAttribute("USER");
		LoginBean bean=(LoginBean)request.getAttribute("bean");
		RegisterBean regbean=(RegisterBean)request.getAttribute("registerbean");
		if(bean!=null)
			out.print("Welcome, "+bean.getName());
			else
			out.print("Welcome,"+regbean.getName());
		
		/*if(name == null || bean == null ||regbean==null|| name.compareTo(bean.getName()) != 0)
		{
	    	String address = "/login-error.jsp";
	    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
	    	dispatcher.forward(request,response);
		}*/
		
	
%>
<p>You are successfully logged in as an Admin!</p>
<% ArrayList<HashMap<String,String>> ans=(ArrayList<HashMap<String,String>>)request.getAttribute("listans");

%>
<!-- <form action="SearchItemController" method="post">
<p>
<input type="text" name="query" />
</p>
<input type="submit" name="add" value="Search Items">
</form>
<form id="main" method="post" name="main" action="" onsubmit="redirect(this);">
    <input type="submit" name="submit"/> 
</form> -->
<form action="AddRegistryController" method="post">
<table>
	<tr>
		<td>Product Name</td>
		<td>Product Description</td>
		<td>Product Price</td>
		<td>Category Name</td>
		<td>Brand Name</td>
		<td>Color Name</td>			
	</tr>	
	<%	for(int i=0;i<ans.size();i++)
		{
		HashMap<String,String>hm= ans.get(i);		
			%><tr>
			<%
					%><td><%=hm.get("product_name") %></td><%
					%><td><%=hm.get("product_desc") %></td><%
					%><td><%=hm.get("product_price") %></td><%
					%><td><%=hm.get("category_name") %></td><%
					%><td><%=hm.get("brand_name") %></td><%
					%><td><%=hm.get("color_name") %></td><%		
					%><td>
					
					<a href="DeleteProductController?product_id=<%=hm.get("product_id")%>">
					Delete Product
					</a>
					</td><%		
				%>
			</tr><% 
		}
		
		%>
</table>
</form>
<%  if(request.getAttribute("success")!=null)
	{%>
		<script type="text/javascript"> window.onload = alertName; </script>
	<% }%>
<%  if(request.getAttribute("failure")!=null)
	{%>
		<script type="text/javascript"> window.onload = alertName1; </script>
	<% }%>

<!-- <form action="DisplayReg" method="post">
<p>
Please click here to view your registry
</p>
<input type="submit" name="add" value="Show Registry">
</form> -->

<!-- <form action="SearchUserController" method="post">
<p>
<input type="text" name="query" />
</p>
<input type="submit" name="add" value="Search Friends">
</form> -->

<form action="fetchcontroller" method="post">
	<button class="btn btn-primary" type="submit" value="login">Add Item</button>
		</form>
<form action="AddFiltersProductController" method="post">
	<input type="text" name="brand">
	
	<button class="btn btn-primary" type="submit" value="login">Add Brand</button>
	
	<input type="text" name="category">
	<button class="btn btn-primary" type="submit" value="login">Add Category</button>
	
	<input type="text" name="color">

	<button class="btn btn-primary" type="submit" value="login">Add Color</button>
		</form>
<form action="LogoutController" method="post">
	<button class="btn btn-primary" type="submit" value="Profile">Log Out</button>
		<input type=hidden name="name" value="<%=session.getAttribute("USER")%>">
		</form>
</body>
</html>