package com.healthcare.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.jasper.compiler.AntCompiler.JasperAntLogger;
import org.json.JSONArray;
import org.json.JSONObject;

public class Patient {

	public String insertPatient(String firstName, String lastName, String age, String gender, String address,
			String mobileNumber, String email, String password) {
		String output = null;
		try {
			String POST_URL = "http://localhost:8080/HealthCarePatientManagement/webapi/patient/registerUser?";
			String POST_PARAMS = "firstName=" + firstName + "&lastName=" + lastName + "&age=" + age + "&gender="
					+ gender + "&address=" + address + "&mobileNumber=" + mobileNumber + "&email=" + email
					+ "&password=" + password;
			URL obj = new URL(POST_URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");

			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(POST_PARAMS.getBytes());
			os.flush();
			os.close();

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in1 = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine1;
				StringBuffer response1 = new StringBuffer();

				while ((inputLine1 = in1.readLine()) != null) {
					response1.append(inputLine1);
				}
				in1.close();

				/* HashMap<String, String> h1 = StringSplitter.proceed(response1.toString()); */
				JSONObject jsonObject = new JSONObject(response1.toString());
				output = jsonObject.get("status").toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}

	public String readPatients(String authString) {
		String output = null;
		String GET_URL = "http://localhost:8080/HealthCarePatientManagement/webapi/patient/getAllUsers";
		try {
			URL get_obj = new URL(GET_URL);
			HttpURLConnection con = (HttpURLConnection) get_obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("authString", authString);
			int get_responseCode = con.getResponseCode();
			if (get_responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				output = "<table border='1' style='width: 100%;text-align:center'><tr><th>FirstName</th><th>LastName</th><th>Age</th><th>Gender</th><th>Address</th><th>MobileNumber</th><th>Email</th><th>Update</th><th>Delete</th></tr>";

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				JSONArray jsonArray = new JSONArray(response.toString());
				for (Object x : jsonArray) {
					JSONObject jsonObject = new JSONObject(x.toString());
					JSONObject login = jsonObject.getJSONObject("login");
					output += "<tr><td><input type=\"hidden\" id=\"hiddenPatientIdUpdate\" name=\"hiddenPatientIdUpdate\" value=\"" + jsonObject.getInt("userId") + "\">" + jsonObject.getString("firstName") + "</td><td>"
							+ jsonObject.getString("lastName") + "</td><td>" + jsonObject.getInt("age") + "</td><td>"
							+ jsonObject.getString("gender") + "</td><td>" + jsonObject.getString("address")
							+ "</td><td>" + jsonObject.getString("mobileNumber") + "</td><td>"
							+ login.getString("email")
							+ "</td><td><input type=\"button\" name=\"btnUpdate\" value=\"Update\" class=\"btnUpdate btn btn-secondary\"></Button</td><td><form method=\"post\" action=\"patientManagement.jsp\"><input type=\"button\" name=\"btnRemove\" value=\"Remove\" class=\"btn btn-danger\"></Button><input type=\"hidden\" name=\"hiddenPatientIDDelete\" value=\"" + jsonObject.getInt("userId") + "\"></form></td></tr>";
				}
				output += "</table>";
			} else {
				System.out.println("GET request not worked");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	public String updatePatient(String userId, String firstName, String lastName, String age, String gender,
			String address, String mobileNumber, String email) {
		return null;
	}

	public String deletePatient(String userId) {
		return null;
	}

}
