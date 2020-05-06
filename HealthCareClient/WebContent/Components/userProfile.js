//On Page Load
$(document).ready(function() {
	$("#firstName").prop("disabled", true);
	$("#lastName").prop("disabled", true);
	$("#age").prop("disabled", true);
	$("#gender").prop("disabled", true);
	$("#address").prop("disabled", true);
	$("#mobileNumber").prop("disabled", true);
	$("#email").prop("disabled", true);
});

//On Update button click

$(document).on("click", "#updateBtn", function(event) {
	$("#cancelBtn").show();
	$("#updateBtn").val("Save");

	if ($("#firstName").prop('disabled') == true) {

	} else {
		var status = validateForm();
		if (status != true) {
			$("#alertError").text(status);
			$("#alertError").show();
			$('#alertError').fadeOut(5000);
			return;
		}

		$.ajax({
			url : "LoginController",
			type : "PUT",
			data : $("#formUser").serialize(),
			dataType : "text",
			complete : function(response, status) {
				onUserUpdateComplete(response.responseText, status);
			}
		});
	}

	$("#firstName").prop("disabled", false);
	$("#lastName").prop("disabled", false);
	$("#age").prop("disabled", false);
	$("#gender").prop("disabled", false);
	$("#address").prop("disabled", false);
	$("#mobileNumber").prop("disabled", false);
	$("#email").prop("disabled", false);
});

//On Update complete

function onUserUpdateComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#cancelBtn").hide();
			$("#updateBtn").val("Update");
			alert("Login again to affect changes.");
			document.location = "index.jsp";
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formUser")[0].reset();
	$('#alertSuccess').fadeOut(3000);
	$('#alertError').fadeOut(3000);
}

//On Cancel Button click

$(document).on("click", "#cancelBtn", function(event) {
	$("#cancelBtn").hide();
	$("#updateBtn").val("Update");

	$("#firstName").prop("disabled", true);
	$("#lastName").prop("disabled", true);
	$("#age").prop("disabled", true);
	$("#gender").prop("disabled", true);
	$("#address").prop("disabled", true);
	$("#mobileNumber").prop("disabled", true);
	$("#email").prop("disabled", true);
});

//Form validation

function validateForm() {

	if ($("#firstName").val().trim() == "") {
		return "Insert your First Name.";
	}

	if ($("#lastName").val().trim() == "") {
		return "Insert your Last Name.";
	}

	if ($("#age").val().trim() == "") {
		return "Insert your age";
	}

	var age = parseInt($("#age").val().trim());
	if (age < 1) {
		return "Insert a valid age.";
	}

	if ($("#gender").val().trim() == "Choose...") {
		return "Select your gender";
	}

	if ($("#address").val().trim() == "") {
		return "Insert your address";
	}

	if ($("#mobileNumber").val().trim() == "") {
		return "Insert your mobile Number";
	}

	if ($("#mobileNumber").val().trim().split("").length < 10) {
		return "Mobile number is not valid";
	}

	if ($("#email").val().trim() == "") {
		return "Insert your email address";
	} else {
		var regExpression = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (!regExpression.test($("#email").val().trim())) {
			return "Email is not valid";
		}
	}

	return true;
}