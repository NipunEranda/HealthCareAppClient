package com.healthcare.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.healthcare.model.Login;
import com.healthcare.utils.StringSplitter;

import jdk.nashorn.internal.parser.JSONParser;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Login login = new Login();
		HashMap<String, String> userDetails = login.login(request.getParameter("email"), request.getParameter("password"));

		if (userDetails.get("status").equalsIgnoreCase("success")) {

			HttpSession session = request.getSession();
			session.setAttribute("email", userDetails.get("email"));
			session.setAttribute("userId", userDetails.get("userId"));
			session.setAttribute("authString", userDetails.get("authString"));
			session.setAttribute("role", userDetails.get("role"));
			
			if (userDetails.get("role").equalsIgnoreCase("admin")) {
				response.sendRedirect("adminPanel.jsp");
			}else {
				response.sendRedirect("homePage.jsp");
			}
		} else {
			response.sendRedirect("login.jsp");
			response.sendError(999, "Login Credentials are not valid");
		}

	}

}
