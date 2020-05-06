//On Login Button Click
$(document).on("click", "#loginBtn", function(event) {
	$("#alertError").text("");
	$("#alertError").hide();

	var status = validateLoginForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	$.ajax({
		url : "LoginController",
		type : "POST",
		data : $("#formLogin").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onLoginComplete(response.responseText, status);
		}
	});
});

//On Login complete
function onLoginComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			document.location = "adminPanel.jsp";
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text("Login Invalid");
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while login.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while login.");
		$("#alertError").show();
	}
}

//Validate Login Form
function validateLoginForm() {
	if ($("#email").val().trim() == "") {
		return "Insert Email";
	} else {
		var regExpression = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (!regExpression.test($("#email").val().trim())) {
			return "Email is not valid";
		}
	}

	if ($("#password").val().trim() == "") {
		return "Insert Password.";
	}
	return true;
}