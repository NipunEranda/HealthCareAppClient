package com.healthcare.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.healthcare.model.Patient;

@WebServlet("/PatientAPI")
public class PatientAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Patient patient = new Patient();

	public PatientAPI() {
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

		String output = patient.insertPatient(request.getParameter("firstName"), request.getParameter("lastName"),
				request.getParameter("age"), request.getParameter("gender"), request.getParameter("address"),
				request.getParameter("mobileNumber"), request.getParameter("email"), request.getParameter("password"));
		response.getWriter().write(output);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map paras = getParasMap(request);
		String output = patient.deletePatient(paras.get("userId").toString());
		response.getWriter().write(output);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map paras = getParasMap(request);
		String output = patient.updatePatient(paras.get("hiddenPatientIdSave").toString(),
				paras.get("firstName").toString(), paras.get("lastName").toString(), paras.get("age").toString(),
				paras.get("gender").toString(), paras.get("address").toString(), paras.get("mobileNumber").toString(),
				paras.get("email").toString());
		response.getWriter().write(output);
	}

}
