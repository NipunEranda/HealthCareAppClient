package com.healthcare.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

public class Login {

	public HashMap<String, String> login(String email, String password) {
		boolean status = false;
		HashMap<String, String> userDetails = new HashMap<>();
		try {

			String POST_URL = "http://localhost:8080/HealthCareLoginManagement/webapi/login/login?";
			String GET_URL = "http://localhost:8080/HealthCareLoginManagement/webapi/login/authorizeUser";
			String POST_PARAMS = "email=" + email + "&password=" + password;

			URL obj = new URL(POST_URL);
			HttpURLConnection con1 = (HttpURLConnection) obj.openConnection();
			con1.setRequestMethod("POST");

			// For POST only - START
			con1.setDoOutput(true);
			OutputStream os = con1.getOutputStream();
			os.write(POST_PARAMS.getBytes());
			os.flush();
			os.close();
			// For POST only - END

			int responseCode = con1.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in1 = new BufferedReader(new InputStreamReader(con1.getInputStream()));
				String inputLine1;
				StringBuffer response1 = new StringBuffer();

				while ((inputLine1 = in1.readLine()) != null) {
					response1.append(inputLine1);
				}
				in1.close();

				JSONObject jsonObject1 = new JSONObject(response1.toString());

				if (jsonObject1.get("status").toString().equalsIgnoreCase("success")) {
					JSONObject jsonObject2 = null;

					URL get_obj = new URL(GET_URL);
					HttpURLConnection con2 = (HttpURLConnection) get_obj.openConnection();
					con2.setRequestMethod("GET");
					con2.setRequestProperty("authString", jsonObject1.get("authString").toString());
					int get_responseCode = con2.getResponseCode();
					if (get_responseCode == HttpURLConnection.HTTP_OK) {
						BufferedReader in2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
						String inputLine2;
						StringBuffer response2 = new StringBuffer();

						while ((inputLine2 = in2.readLine()) != null) {
							response2.append(inputLine2);
						}
						in2.close();

						
						jsonObject2 = new JSONObject(response2.toString());
					} else {
						System.out.println("GET request not worked");
					}

					userDetails.put("status", "success");
					userDetails.put("email", email);
					userDetails.put("userId", jsonObject1.get("userId").toString());
					userDetails.put("authString", jsonObject1.get("authString").toString());
					userDetails.put("role", jsonObject2.get("role").toString());

				} else {
					userDetails.put("status", "fail");
				}

			} else {
				System.out.println("POST request not worked");
				userDetails.put("status", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDetails;
	}

	public JSONObject getUserDetails(String authString, String userId) {
		JSONObject jsonObject = null;
		try {
			String GET_URL = "http://localhost:8080/HealthCareLoginManagement/webapi/login/getUserDetails/"+ userId;
			URL get_obj = new URL(GET_URL);
			
			HttpURLConnection con = (HttpURLConnection) get_obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("authString", authString);
			int get_responseCode = con.getResponseCode();
			
			if (get_responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine2;
				StringBuffer response2 = new StringBuffer();

				while ((inputLine2 = in.readLine()) != null) {
					response2.append(inputLine2);
				}
				in.close();

				jsonObject = new JSONObject(response2.toString());
			} else {
				System.out.println("GET request not worked");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

}
