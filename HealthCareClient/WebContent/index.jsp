<%@page import="com.healthcare.model.Login"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<link href="Views/login.css" rel="stylesheet">
<link href="Views/main.css" rel="stylesheet">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="Components/login.js"></script>
<script src="Components/main.js"></script>
</head>
<body background="images/background.jpg" style="background-size: cover;">

	<div class="wrapper fadeInDown">
		<div id="formContent">

			<div class="fadeIn first">
				<img src="images/logo.jpg" id="icon" alt="User Icon"
					style="width: 200px; height: 200px;" />
			</div>

			<form id="formLogin">
				<input type="email" class="fadeIn second" name="email" id="email"
					placeholder="email"> <input type="password" id="password"
					class="fadeIn third" name="password" placeholder="password">
				<input type="button" class="fadeIn fourth" id="loginBtn"
					value="Log In">
				<div id="alertError" class="alert alert-danger"></div>
			</form>

			<div id="formFooter">
				<a class="underlineHover" href="#">Forgot Password?</a>
			</div>

		</div>
		<%
			if (request.getParameter("logout") != null) {
				if (request.getParameter("logout").equalsIgnoreCase("true")) {
					session.invalidate();
					response.sendRedirect("index.jsp");
				}
			}
		%>
	</div>
</body>
</html>