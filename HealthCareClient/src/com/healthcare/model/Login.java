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

import com.healthcare.utils.StringSplitter;

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

				HashMap<String, String> h1 = StringSplitter.proceed(response1.toString());

				if (h1.get("status").equalsIgnoreCase("success")) {
					HashMap<String, String> h2 = null;

					URL get_obj = new URL(GET_URL);
					HttpURLConnection con2 = (HttpURLConnection) get_obj.openConnection();
					con2.setRequestMethod("GET");
					con2.setRequestProperty("authString", h1.get("authString"));
					int get_responseCode = con2.getResponseCode();
					if (get_responseCode == HttpURLConnection.HTTP_OK) {
						BufferedReader in2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
						String inputLine2;
						StringBuffer response2 = new StringBuffer();

						while ((inputLine2 = in2.readLine()) != null) {
							response2.append(inputLine2);
						}
						in2.close();

						h2 = StringSplitter.proceed(response2.toString());
					} else {
						System.out.println("GET request not worked");
					}

					userDetails.put("status", "success");
					userDetails.put("email", email);
					userDetails.put("userId", h1.get("userId"));
					userDetails.put("authString", h1.get("authString"));
					userDetails.put("role", h2.get("role"));

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

	public HashMap<String, String> getUserDetails(String authString, String userId) {
		HashMap<String, String> userDetails = new HashMap<>();
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

				userDetails = StringSplitter.proceed(response2.toString());
			} else {
				System.out.println("GET request not worked");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDetails;
	}

}
