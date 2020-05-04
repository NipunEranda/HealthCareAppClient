package com.healthcare.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

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

				/*HashMap<String, String> h1 = StringSplitter.proceed(response1.toString());*/
				JSONObject jsonObject = new JSONObject(response1.toString());
				output = jsonObject.get("status").toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}

	public String readPatients() {
		return null;
	}

	public String updatePatient(String userId, String firstName, String lastName, String age, String gender,
			String address, String mobileNumber, String email) {
		return null;
	}

	public String deletePatient(String userId) {
		return null;
	}

}
