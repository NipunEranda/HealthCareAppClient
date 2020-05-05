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
import javax.sound.midi.Soundbank;

import org.json.JSONObject;

public class Login {

	private String authString;

	public JSONObject login(String email, String password) {
		boolean status = false;
		JSONObject userDetails = new JSONObject();
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
						userDetails.put("status", "error");
						System.out.println("GET request not worked");
					}
					if (jsonObject1.getString("status").equalsIgnoreCase("success")) {
						this.authString = jsonObject1.get("authString").toString();
						userDetails.put("status", "success");
						userDetails.put("email", email);
						userDetails.put("userId", jsonObject1.get("userId").toString());
						userDetails.put("authString", jsonObject1.get("authString").toString());
						userDetails.put("role", jsonObject2.get("role").toString());
					} else {
						userDetails.put("status", "error");
					}
				} else {
					userDetails.put("status", "error");
				}

			} else {
				System.out.println("POST request not worked");
				userDetails.put("status", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDetails;
	}

	public JSONObject getUserDetails(String authString, String userId) {
		JSONObject jsonObject = null;
		try {
			String GET_URL = "http://localhost:8080/HealthCareLoginManagement/webapi/login/getUserDetails/" + userId;
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

		} catch (Exception e) {		Login login = new Login();
			e.printStackTrace();
		}
		return jsonObject;
	}

	public JSONObject updateUserDetails(String userId, String firstName, String lastName, String age, String gender,
			String address, String mobileNumber, String email, String authString) {
		JSONObject resObj = new JSONObject();

		try {
			String PUT_URL = "http://localhost:8080/HealthCareLoginManagement/webapi/login/updateUserDetails?";
			String PUT_PARAMS = "userId=" + userId + "&firstName=" + firstName + "&lastName=" + lastName + "&age=" + age
					+ "&gender=" + gender + "&address=" + address + "&mobileNumber=" + mobileNumber + "&email=" + email;
			URL obj = new URL(PUT_URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("PUT");	
			con.setRequestProperty("authString", authString);
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(PUT_PARAMS.getBytes());
			os.flush();
			os.close();

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in1 = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine1;
				StringBuffer response = new StringBuffer();

				while ((inputLine1 = in1.readLine()) != null) {
					response.append(inputLine1);
				}
				in1.close();
				System.out.println(response.toString());
				JSONObject jsonObject = new JSONObject(response.toString());
				if (jsonObject.getString("status").equalsIgnoreCase("success")) {
					// String newUserDetails = getUserDetails(authString, userId).toString();
					resObj.put("status", "success");
					// resObj.put("data", newUserDetails);
				} else {
					resObj.put("status", "error");
					resObj.put("data", "Error while updating the item.");
				}
			}

		} catch (Exception e) {
			resObj.put("status", "error");
			resObj.put("data", "Error while updating the item.");
			System.err.println(e.getMessage());
		}
		return resObj;
	}
	
	public String getAuthString() {
		return this.authString;
	}

}
