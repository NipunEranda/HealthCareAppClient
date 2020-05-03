<%@page import="java.util.HashMap"%>
<%@page import="com.healthcare.model.Login"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Home Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style type="text/css">
.baseContainer {
	margin: 20px;
}
</style>
</head>
<body>

	<div class="baseContainer">
		<nav class="navbar navbar-expand-md navbar-light bg-light">
			<a href="#" class="navbar-brand"> <img src="images/logoSmall.jpg"
				height="50" alt="CoolBrand">
			</a>
			<button type="button" class="navbar-toggler" data-toggle="collapse"
				data-target="#navbarCollapse">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarCollapse">
				<div class="navbar-nav">
					<a href="#" class="nav-item nav-link active">Home</a> <a href="#"
						class="nav-item nav-link">Profile</a> <a href="#"
						class="nav-item nav-link">Messages</a> <a href="#"
						class="nav-item nav-link disabled" tabindex="-1">Reports</a>
				</div>
				<div class="navbar-nav ml-auto">
					<a href="login.jsp?logout=true" class="nav-item nav-link"
						name="logout" value="logout">Logout</a>
				</div>
			</div>
		</nav>
		<div>
			<%
				Login login = new Login();
				HashMap<String, String> userDetails = login.getUserDetails((String) session.getAttribute("authString"),
						(String) session.getAttribute("userId"));
				out.print(userDetails.get("firstName"));
			%>
		</div>
	</div>
</body>
</html>