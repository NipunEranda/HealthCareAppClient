<%@page import="org.json.JSONObject"%>
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
<title>Admin Panel</title>
<script src="Components/popper.min.js"></script>
<script src="Components/jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
<script src="Components/main.js"></script>

<link href="Views/bootstrap.min.css" rel="stylesheet">
<link href="Views/font-awesome.min.css" rel="stylesheet">
<link href="Views/adminPanel.css" rel="stylesheet">
<link href="Views/main.css" rel="stylesheet">
<style type="text/css">
.baseContainer {
	margin: 20px;
}
</style>
</head>
<body background="images/background.jpg" style="background-size: cover;">
	<%
		if (session.getAttribute("email") == null) {
			response.sendRedirect("index.jsp");
		} else {
	%>
	<div class="fadeInDown">
		<div class="baseContainer">
			<nav class="navbar navbar-expand-md navbar-light bg-light">
				<a href="#" class="navbar-brand"> <img
					src="images/logoSmall.jpg" height="50" alt="CoolBrand">
				</a>
				<button type="button" class="navbar-toggler" data-toggle="collapse"
					data-target="#navbarCollapse">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarCollapse">
					<div class="navbar-nav">
						<a href="adminPanel.jsp" id="homeNav"
							class="nav-item nav-link active">Home</a> <a
							href="userProfile.jsp" id="profileNav" class="nav-item nav-link">Profile</a>
					</div>
					<div class="navbar-nav ml-auto" id="logoutNav">
						<label class="nav-item nav-link logout">logout</label>
					</div>
				</div>
			</nav>

			<div>
				<br />
				<h1>
					Welcome
					<%
					Login login = new Login();
						JSONObject userDetails = login.getUserDetails((String) session.getAttribute("authString"),
								(String) session.getAttribute("userId"));
						out.print(userDetails.get("firstName"));
				%>
				</h1>
				<br />
				<div class="container">
					<div class="row">
						<div class="col">
							<div class="row">
								<div class="col c" style="padding: 25px;">
									<div class="cc" onclick="location.href='patientManagement.jsp'">
										<label>Patient Management</label>
									</div>
								</div>
								<div class="col c" style="padding: 25px;">
									<div class="cc">
										<label>Doctor Management</label>
									</div>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="row">
								<div class="col c" style="padding: 25px;">
									<div class="cc">
										<label>Hospital Management</label>
									</div>
								</div>
								<div class="col c" style="padding: 25px;">
									<div class="cc">
										<label>User Management</label>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col">
							<div class="row">
								<div class="col c" style="padding: 25px;">
									<div class="cc">
										<label>Hospital Management</label>
									</div>
								</div>
								<div class="col c" style="padding: 25px;">
									<div class="cc">
										<label>Appointment Management</label>
									</div>
								</div>
								<div class="col c" style="padding: 25px;">
									<div class="cc">
										<label>Payment Management</label>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
		}
	%>
</body>
</html>