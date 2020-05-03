$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// Save Button Controller
$(document).on("click", "#btnSave", function(event) {
});

// Clear status messages
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();

// Form Validation
// Form validation-------------------
var status = validateLoginForm();
// If not valid
if (status != true) {
	$("#alertError").text(status);
	$("#alertError").show();
	return;
}

function validateItemForm() {
	// Validations

	if ($("#emailTxt").val().trim() == "") {
		return "Insert Email";
	}

	if ($("#passwordTxt").val().trim() == "") {
		return "Insert Email";
	}

	return true;
}