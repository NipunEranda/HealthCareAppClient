<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient Management</title>
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
<script src="Components/patient.js"></script>
<link href="Views/patientManagement.css" rel="stylesheet">
<style type="text/css">
.baseContainer {
	margin: 20px;
}
</style>
</head>
<body>
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
						<a href="adminPanel.jsp" class="nav-item nav-link active">Home</a>
						<a href="#" class="nav-item nav-link">Profile</a> <a href="#"
							class="nav-item nav-link">Messages</a> <a href="#"
							class="nav-item nav-link disabled" tabindex="-1">Reports</a>
					</div>
					<div class="navbar-nav ml-auto">
						<a href="index.jsp?logout=true" class="nav-item nav-link">Logout</a>
					</div>
				</div>
			</nav>
		</div>

		<div class="container">

			<div>
				<h1>Patient Details</h1>
				<br />
				<form id="formPatient" action="" method="post">
					<div class="form-group">
						<div class="row">
							<div class="col">
								<label for="firstName">First Name</label> <input type="text"
									class="form-control" placeholder="First name" id="firstName"
									name="firstName" />
							</div>
							<div class="col">
								<label for="lastName">Last Name</label> <input type="text"
									class="form-control" placeholder="Last name" id="lastName"
									name="lastName" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col">
								<label for="age">Age</label> <input type="number"
									class="form-control" id="age" name="age" placeholder="Age">
							</div>
							<div class="col">
								<label for="gender">Gender</label> <select id="gender"
									name="gender" class="form-control">
									<option selected>Choose...</option>
									<option>Male</option>
									<option>Female</option>
								</select>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col">
								<label for="address">Address</label> <input type="text"
									class="form-control" id="address" name="address"
									placeholder="Address">
							</div>
							<div class="col">
								<label for="mobileNumber">Mobile Number</label> <input
									type="text" class="form-control" placeholder="Mobile Number"
									id="mobileNumber" name="mobileNumber">
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="email">Email address</label> <input type="email"
							class="form-control" id="email" name="email" placeholder="Email">
						<small id="emailSmallText" class="form-text text-muted">We'll
							never share your email with anyone else.</small>
					</div>

					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="Password" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters."> <small id="passwordSmallText"
							class="form-text text-muted">Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters.</small>
					</div>
					<div id="alertSuccess" class="alert alert-success">
						<%
							if (session.getAttribute("statusMsg") != null) {
								out.print(session.getAttribute("statusMsg"));
							}
						%>
					</div>
					<div id="alertError" class="alert alert-danger"></div>
					<input type="button" class="btn btn-primary" id="saveBtn"
						name="saveBtn" value="Save"> <input type="hidden"
						id="hiddenPatientId" name="hiddenPatientId" value="" />

				</form>
			</div>
		</div>
</body>
</html>