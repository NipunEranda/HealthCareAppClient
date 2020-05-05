<%@page import="com.healthcare.model.Patient"%>
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
<script src="Components/main.js"></script>
<link href="Views/patientManagement.css" rel="stylesheet">
<style type="text/css">
.baseContainer {
	margin: 20px;
}
</style>

<%
	if (session.getAttribute("statusMsg") != null) {
%>
<style>
.alert .alert-success {
	display: block;
}
</style>
<%
	} else {
%>
<style>
.alert .alert-success {
	display: none;
}

.alert .alert-danger {
	display: none;
}
</style>

<%
	}
%>

</head>
<body>

	<%
		Patient patient = new Patient();
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
						<a href="adminPanel.jsp" id="homeNav" class="nav-item nav-link">Home</a>
						<a href="userProfile.jsp" id="profileNav"
							class="nav-item nav-link">Profile</a>
					</div>
					<div class="navbar-nav ml-auto">
						<a href="index.jsp?logout=true" id="logoutNav"
							class="nav-item nav-link">Logout</a>
					</div>
				</div>
			</nav>
		</div>
		<br />
		<div class="container">
			<div>
				<h1>Manage Patient Details</h1>
				<br />
				<form id="formPatient">
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
						<div class="row">
							<div class="col">
								<label for="email">Email address</label> <input type="email"
									class="form-control" id="email" name="email"
									placeholder="Email"> <small id="emailSmallText"
									class="form-text text-muted">We'll never share your
									email with anyone else.</small>
							</div>
							<div class="col">
								<label for="password">Password</label> <input type="password"
									class="form-control" id="password" name="password"
									placeholder="Password"
									title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters.">
								<small id="passwordSmallText" class="form-text text-muted">Must
									contain numbers,uppercase,lowercase letters, and at least 8 or
									more characters.</small>
							</div>
						</div>
					</div>

					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<input type="button" class="btn btn-primary" id="saveBtn"
						name="saveBtn" value="Save">&nbsp;<input type="button"
						class="btn btn-danger" id="cancelBtn" name="cancelBtn"
						value="Cancel"> <input type="hidden"
						id="hiddenPatientIdSave" name="hiddenPatientIdSave" value="" /><input
						type="hidden" id="authString" name="authString"
						value="<%out.print(session.getAttribute("authString"));%>" />

				</form>
			</div>
			<br />
			<div id="divPatientsGrid">
				<h1>Patients List</h1>
				<br />
				<%
					out.print(patient.readPatients(session.getAttribute("authString").toString()));
				%>
			</div>
		</div>
	</div>
</body>
</html>