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
 
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
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
    elem.setAttribute("action","welcome_page.jsp");
    elem.submit();
}
</script> 
<style>
body, html {
    height: 100%;
}
.jumbotron { 
    /* The image used */
    background-image: url("https://mdbootstrap.com/img/Photos/Horizontal/Nature/full page/img(11).jpg");

    /* Half height */
    height: 50%; 

    /* Center and scale the image nicely */
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}
</style>
<div class="jumbotron"><center><h1 style="color:#407bb2">Incognito Registry</h1></center></div>
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

<p>You are successfully logged in!</p>

<form action="CreateRegController" method="post">

<input type="submit" name="add" value="Create your registry" class="btn btn-primary">
</form>

<br>
<br>
<form action="DisplayReg" method="post">
<p>
Please click here to view your registry
</p>
<input type="submit" name="add" value="Show Registry" class="btn btn-primary">
</form>
<br>

<br>
<form action="fetchprofile" method="post">
	<button class="btn btn-primary" type="submit" value="Profile">Set Profile</button>
		<input type=hidden name="name" value="<%=session.getAttribute("USER")%>">
		</form>
<br>
<form action="sharedRegistry" method="post">
	<button class="btn btn-primary" type="submit" value="Profile">Shared Registry</button>
		<input type=hidden name="name" value="<%=session.getAttribute("USER")%>">
		</form>	
<br>
<%-- <a href="showFriend?user=<%=session.getAttribute("USER")%>">Show Friends</a>

 --%>
<form action="LogoutController" method="post">
	<button class="btn btn-primary" type="submit" value="Profile">Log Out</button>
		<input type=hidden name="name" value="<%=session.getAttribute("USER")%>">
		</form>
</body>
</html>