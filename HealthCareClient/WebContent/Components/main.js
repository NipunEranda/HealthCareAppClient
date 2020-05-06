//On Page load
$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	$("#cancelBtn").hide();
});

//On logout click
$(document).on("click", "#logoutNav", function(event) {
	$.ajax({
		url : "LoginController",
		type : "DELETE",
		data : "",
		dataType : "text",
		complete : function(response, status) {
			onUserLogout(response.responseText, status);
		}
	});
});

//On Logout
function onUserLogout(response, status) {
	if (status == "success") {
		document.location = "index.jsp";
	}

}