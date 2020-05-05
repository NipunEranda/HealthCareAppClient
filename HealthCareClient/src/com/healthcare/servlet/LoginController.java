package com.healthcare.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.healthcare.model.Login;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Login login;
	public LoginController() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		login = new Login();
		JSONObject userDetails = login.login(request.getParameter("email"), request.getParameter("password"));
		String output;
		if (userDetails.get("status").toString().equalsIgnoreCase("success")) {
			
			HttpSession session = request.getSession();
			session.setAttribute("email", userDetails.get("email"));
			session.setAttribute("userId", userDetails.get("userId"));
			session.setAttribute("authString", userDetails.get("authString"));
			session.setAttribute("role", userDetails.get("role"));

			output = "{\"status\":\"success\", \"data\":\"\"}";

		} else {
			output = "{\"status\":\"error\", \"data\":\"\"}";
		}
		response.getWriter().write(output);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.invalidate();
		response.getWriter().write("success");

	}

	/*@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("userId"));
		System.out.println(request.getParameter("firstName"));
		System.out.println(request.getParameter("lastName"));
		System.out.println(request.getParameter("age"));
		System.out.println(request.getParameter("gender"));
		System.out.println(request.getParameter("address"));
		System.out.println(request.getParameter("mobileNumber"));
		System.out.println(request.getParameter("email"));
		JSONObject obj = login.updateUserDetails(request.getParameter("userId"), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("age"), request.getParameter("gender"), request.getParameter("address"), request.getParameter("mobileNumber"), request.getParameter("email"), login.getAuthString());		
		String output = obj.toString();
		response.getWriter().write(output);
	}*/
	
	

}
