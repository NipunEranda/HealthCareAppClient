package com.healthcare.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Patient {

	public String authString;

	public String readPatients(String authString) {
		this.authString = authString;
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
					output += "<tr><td><input type='hidden' id='authString' name='authString' value='" + authString
							+ "'><input type='hidden' id='hiddenPatientIdUpdate' name='hiddenPatientIdUpdate' value='"
							+ jsonObject.getInt("userId") + "'>" + jsonObject.getString("firstName") + "</td><td>"
							+ jsonObject.getString("lastName") + "</td><td>" + jsonObject.getInt("age") + "</td><td>"
							+ jsonObject.getString("gender") + "</td><td>" + jsonObject.getString("address")
							+ "</td><td>" + jsonObject.getString("mobileNumber") + "</td><td>"
							+ login.getString("email")
							+ "</td><td><input type='button' name='btnUpdate' value='Update' class='btnUpdate btn btn-secondary'></td><td><Input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-authString='"
							+ authString + "' data-userId='" + jsonObject.getInt("userId") + "'></td></tr>";
				}
				output += "</table>";
			} else {
				System.out.println("GET request not worked");
			}
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertPatient(String firstName, String lastName, String age, String gender, String address,
			String mobileNumber, String email, String password, String authString) {
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

				/*
				 * JSONObject jsonObject = new JSONObject(response1.toString()); output =
				 * jsonObject.get("status").toString();
				 */
				String newPatients = readPatients(authString);
				output = "{\"status\":\"success\", \"data\":\"" + newPatients + "\"}";
			}

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String updatePatient(String userId, String firstName, String lastName, String age, String gender,
			String address, String mobileNumber, String email, String authString) {
		String output = null;
		try {
			String POST_URL = "http://localhost:8080/HealthCarePatientManagement/webapi/patient/updateUser?";
			String POST_PARAMS = "userId=" + userId + "&firstName=" + firstName + "&lastName=" + lastName + "&age="
					+ age + "&gender=" + gender + "&address=" + address + "&mobileNumber=" + mobileNumber + "&email="
					+ email;
			URL obj = new URL(POST_URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("PUT");
			con.setRequestProperty("authString", authString);
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

				/*
				 * JSONObject jsonObject = new JSONObject(response1.toString()); output =
				 * jsonObject.getString("status").toString();
				 */
				String newPatients = readPatients(authString);
				output = "{\"status\":\"success\", \"data\":\"" + newPatients + "\"}";
			}

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deletePatient(String userId, String authString) {
		String output = null;
		try {
			String POST_URL = "http://localhost:8080/HealthCarePatientManagement/webapi/patient/" + userId;
			URL obj = new URL(POST_URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("DELETE");
			con.setRequestProperty("authString", authString);
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
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

				/*
				 * JSONObject jsonObject = new JSONObject(response1.toString()); output =
				 * jsonObject.getString("status").toString();
				 */
				String newPatients = readPatients(authString);
				output = "{\"status\":\"success\", \"data\":\"" + newPatients + "\"}";
			}

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
