//On page load
$(document).on("click", ".btnUpdate", function(event) {
	$("#cancelBtn").show();
	$("#hiddenPatientIdSave").val(
	$(this).closest("tr").find('#hiddenPatientIdUpdate').val());
	$("#firstName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#lastName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#age").val($(this).closest("tr").find('td:eq(2)').text());
	$("#gender").val($(this).closest("tr").find('td:eq(3)').text());
	$("#address").val($(this).closest("tr").find('td:eq(4)').text());
	$("#mobileNumber").val($(this).closest("tr").find('td:eq(5)').text());
	$("#email").val($(this).closest("tr").find('td:eq(6)').text());
	$("#saveBtn").val("Update");
	$("#password").prop("disabled", true);
});

//On Save Button Click

$(document).on("click", "#saveBtn", function(event) {

	var type = ($("#hiddenPatientIdSave").val() == "") ? "POST" : "PUT";
	if (type == "POST") {
		var status = validateForm();
		if (status != true) {
			$("#alertError").text(status);
			$("#alertError").show();
			$('#alertError').fadeOut(5000);
			return;
		}
	} else {
		var status = validateUpdateForm();
		if (status != true) {
			$("#alertError").text(status);
			$("#alertError").show();
			$('#alertError').fadeOut(5000);
			return;
		}
	}
	$.ajax({
		url : "PatientAPI",
		type : type,
		data : $("#formPatient").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onPatientDetailsSaveComplete(response.responseText, status);
		}
	});
});

//On Remove Button Click

$(document).on("click", ".btnRemove", function(event) {
	var userId = $(this).attr('data-userId');
	var authString = $(this).attr('data-authString');
	$.ajax({
		url : "PatientAPI",
		type : "DELETE",
		data : "userId=" + userId + "&authString=" + authString,
		dataType : "text",
		complete : function(response, status) {
			onPatientDetailsDeleteComplete(response.responseText, status);
		}
	});
});

//On Save complete

function onPatientDetailsSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#cancelBtn").hide();
			$("#saveBtn").val("Save");
			$("#divPatientsGrid").html(resultSet.data);
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

	$("#formPatient")[0].reset();
	$('#alertSuccess').fadeOut(3000);
	$('#alertError').fadeOut(3000);
}

//On Delete Complete

function onPatientDetailsDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divPatientsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
	$('#alertSuccess').fadeOut(3000);
	$('#alertError').fadeOut(3000);
}

//On Cancel Button Click

$(document).on("click", "#cancelBtn", function(event) {
	$("#cancelBtn").hide();
	$("#saveBtn").val("Save");
	$("#password").prop("disabled", false);
	$("#hiddenPatientIdSave").value = "";
	$("#firstName").val("");
	$("#lastName").val("");
	$("#age").val("");
	$("#gender").val("Choose...");
	$("#address").val("");
	$("#mobileNumber").val("");
	$("#email").val("");
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

	if ($("#password").val().trim() == "") {
		return "Insert a password";
	} else {
		var lowerCaseLetters = /[a-z]/g;
		var upperCaseLetters = /[A-Z]/g;
		var numbers = /[0-9]/g;

		if (!$("#password").val().trim().match(lowerCaseLetters)) {
			return "Password should contain atleat one lowercase latter.";
		}
		if (!$("#password").val().trim().match(upperCaseLetters)) {
			return "Password should contain atleat one uppdercase latter.";
		}
		if (!$("#password").val().trim().match(numbers)) {
			return "Password should contain atleat one number value.";
		}
		if ($("#password").val().trim().split("").length < 8) {
			return "Password should contain atleast 8 letters";
		}
	}

	return true;
}

//Update Form validation

function validateUpdateForm() {

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