package com.healthcare.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
	
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
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
		response.getWriter().write("{\"status\":\"success\"}");
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		JSONObject obj = login.updateUserDetails(paras.get("userId").toString(), paras.get("firstName").toString(), paras.get("lastName").toString(), paras.get("age").toString(), paras.get("gender").toString(), paras.get("address").toString(), paras.get("mobileNumber").toString(), paras.get("email").toString(), paras.get("authString").toString());		
		String output = obj.toString();
		response.getWriter().write(output);
	}
	
	

}
