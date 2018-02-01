<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@page import="java.util.ArrayList"%>
<title>Insert Product</title>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</head>
<body>
<div >



<h1>Product Details</h1>
		<form class="form-signin" action="InsertProductController"
			method="post">
			
			
				Product Name : <label for="inputProductname" class="sr-only">Product Name<span class="req">*</span></label> <input
				type="text" id="inputProductname" class="form-control"
				placeholder="Product Name" name="productname" required autofocus>
			Product Description : <label for="inputProductdes" class="sr-only">Product Description<span class="req">*</span></label> <input
				type="text" id="inputProductdes" class="form-control"
				placeholder="Product Description" name="productdesc" required>
			Price : <label for="inputPrice" class="sr-only">Price<span class="req">*</span></label> <input
				type="text" id="inputPrice" class="form-control"
				placeholder="Price" name="price" required>
			Product Brand : <select name="brand">
<%

String[] brand=(String[])request.getAttribute("brand");
//list=request.getAttribute("brand");
//out.println(list.length);
%><% 
for(int i=0;i<brand.length;i++)
		{
		

			%><option value="<%=brand[i]%>"><%=brand[i]%>
			</option>
			<%
			}
		
		%>
</select>
<br>
			Product Color : <select name="color">
<%

String[] color=(String[])request.getAttribute("color");
//list=request.getAttribute("brand");
//out.println(list.length);
%><% 
for(int i=0;i<color.length;i++)
		{
		

			%><option value="<%=color[i]%>"><%=color[i]%>
			</option>
			<%
			}
		
		%>
</select>
<br>
			Product Category : <select name="category">
<%

String[] category=(String[])request.getAttribute("category");
//list=request.getAttribute("brand");
//out.println(list.length);
%><% 
for(int i=0;i<category.length;i++)
		{
		

			%><option value="<%=category[i]%>"><%=category[i]%>
			</option>
			<%
			}
		
		%>
</select>
<br>

			



			<button class="btn btn-lg btn-primary btn-block" type="submit" value="login">Add Product</button>
		</form>
</div>
</body>

<form action="AdminHome.jsp" method="post">
<p>

</p>
<input type="submit" name="add" value="Go to Home" class="btn btn-primary">
</form>
</html>