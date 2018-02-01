<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<p>
<h1>Update User Profile</h1>
</p>
<form action="UpdateProfile" method="post">
		Name:<input type="text" name="name" value=<%=request.getAttribute("name") %> readonly><br>
		First Name:<input type="text" name="first_name" value=<%=request.getAttribute("first_name") %>><br>
		Last Name:<input type="text" name="last_name" value=<%=request.getAttribute("last_name") %>><br>
		
		Email Id:<input type="text" name="emailid" value=<%=request.getAttribute("emailid") %>><br>
		Mobile No :<input type="text" name="mobileno" value=<%=request.getAttribute("mobileno") %>><br>
		Address :<input type="text" name="address" value=<%=request.getAttribute("address") %>><br>
		Who is your best friend?:<input type="text" name="securityans" value=<%=request.getAttribute("securityans") %>><br>
		<%-- <input TYPE=checkbox name=shared <%if(request.getAttribute("shared").equals("1")){ %>checked<%} %>
		
		> Do you Want to share your registry with your friends? <BR> --%>
		<input type="submit" value="Update Profile" class="btn btn-primary">
	</form>
	</center>
	
	<form action="welcome_page.jsp" method="post">

<input type="submit" name="add" value="Go to Home" class="btn btn-primary">
</form>
</body>
</html>