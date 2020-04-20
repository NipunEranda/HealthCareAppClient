<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
	<div class="container">
		<form action="LoginAPI" method="post">
			<div class="input-group input-group-sm mb-3">
				<input type="text" name="email" id="emailTxt" /><br />
			</div>
			<div class="input-group input-group-sm mb-3">
				<input type="text" name="password" id="passwordTxt" /><br />
			</div>
			<div class="input-group input-group-sm mb-3">
				<input type="submit" value="login" id="loginBtn" />
			</div>
			<div hidden id="alertSuccess" class="alert alert-success"></div>
			<div hidden id="alertError" class="alert alert-danger"></div>
		</form>
	</div>
</body>
</html>