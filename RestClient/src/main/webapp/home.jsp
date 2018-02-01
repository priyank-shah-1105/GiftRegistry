<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../../../favicon.ico">

<title>Signin in Incognito Gift Registry</title>

<!-- Bootstrap core CSS -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<!-- Custom styles for this template -->
<link href="signin.css" rel="stylesheet">
<style>

h1 {
	text-align: center;
}
</style>


</head>

<body>


	 <h1>Incognito</h1>
	 
	 <table class="table table-striped">
		<tr>
			<td>
			
			<div class="container" id="login">
	<h1>Welcome Back!</h1>
		<form class="form-signin" action="LoginController" method="post">
			
			
				<label for="inputUsername" class="sr-only">User Name<span class="req">*</span></label> <input
				type="text" id="inputUsername" class="form-control"
				placeholder="Username" name="name" required autofocus>
			<label for="inputPassword" class="sr-only">Password<span class="req">*</span></label> <input
				type="password" id="inputPassword" class="form-control"
				placeholder="Password" name="password" required>
			
			<button class="btn btn-lg btn-primary btn-block" type="submit" value="login">Sign
				in</button>
		</form>

	</div>
			</td>
			<td>
	
			<form class="form-signin" action="RegisterController"
			method="post">
			<h1>Sign Up</h1>
			
			<label for="inputEmail" class="sr-only">Email address<span class="req">*</span></label> <input
				type="email" id="inputEmail" class="form-control"
				placeholder="Email address" name="email" required autofocus>
			<label for="inputUsername" class="sr-only">User Name<span class="req">*</span></label> <input
				type="text" id="inputUsername" class="form-control"
				placeholder="Username" name="username" required>
			<label for="inputPassword" class="sr-only">Password<span class="req">*</span></label> <input
				type="password" id="inputPassword" class="form-control"
				placeholder="Set A Password" name="password" required>
			
			<button class="btn btn-lg btn-primary btn-block" type="submit">Get Started</button>
		</form>
		
		<form action="welcome_page.jsp" method="post">
<p>

</p>
<input type="submit" name="add" value="Go to Home">
</form>
			</td>
		</tr>
	</table>
</body>
</html>
