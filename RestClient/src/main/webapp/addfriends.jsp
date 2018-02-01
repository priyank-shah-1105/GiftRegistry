<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
   
<%

String[] friends=(String[])request.getAttribute("friends");
//out.println(friends[0]);
//list=request.getAttribute("brand");
//out.println(list.length);
%>

<div class="table-responsive">
		<!--<table class="table table-striped" onmouseover="isKeyPressed(event,this.style)">-->
		

		
		<!--	onmouseover="isKeyPressed(event,this.style)"
			onmousemove="isaltkeyup(event,this.style)"-->
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Friends</th>
					</tr>
			</thead>
			<tbody id="data" >
		<%	for(int i=0;i<friends.length;i++)
		{ %>
				<tr>
					<td><%=friends[i]%></td>
					
					<td>
						<button type="button" class="btn btn-info "
							onclick="deleteRow(this)">Delete</button>
					</td>
				</tr>
				<%
				}%>
			</tbody>

		</table>
		<button type="button" id="button1" Onclick="myFunction()" class="btn">Search Users to Add into your friends list</button>
	</div>

<form action="AddFriendController" method="post">
<p>
<input type="text" name="query" />
</p>
<input type="submit" name="add" value="Search Friends" class="btn btn-primary">
</form>
<form action="welcome_page.jsp" method="post">
<p>

</p>
<input type="submit" name="add" value="Go to Home" class="btn btn-primary">
</form>
</body>
</html>
