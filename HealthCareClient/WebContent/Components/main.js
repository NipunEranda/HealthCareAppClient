$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	$("#cancelBtn").hide();
});

$(document).on("click", "#logoutNav", function(event) {
	alert("LOL");
});