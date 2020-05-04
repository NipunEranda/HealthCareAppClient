package com.healthcare.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.JsonObject;
import com.healthcare.model.User;


public class PatientManagementServiceImp implements PatientManagementService {

	@Override
	public JsonObject RegisterUser(String firstName, String lastName, String age, String gender,
			String address, String mobileNumber, String email, String password) {
		JsonObject j = DBManager.registerUser(firstName, lastName, age, gender, address, mobileNumber,email, password);
		return j;
	}

	@Override
	public JsonObject getUserDetails(String userId) {
		JsonObject user = DBManager.getUserDetails(userId);
		return user;
	}
	
	@Override
	public JsonObject getUserDetailsByLoginId(String loginId) {
		JsonObject user = DBManager.getUserDetailsByLoginId(loginId);
		return user;
	}

	@Override
	public List<String> getAllUsers() {
		List<String> allUsers;
		allUsers = DBManager.getAllUsers();
		return allUsers;
	}

	@Override
	public JsonObject deleteUser(String userId) {
		JsonObject user = DBManager.deleteUser(userId);
		return user;
	}

	@Override
	public JsonObject updateUser(String userId, String firstName, String lastName, String age, String gender, String address,
			String mobileNumber, String email) {
		JsonObject user = DBManager.updateUser(userId, firstName, lastName, age, gender, address, mobileNumber, email);
		return user;
	}

	@Override
	public JsonObject recordPatientCondition(String userId, String patientCondition) {
		JsonObject j = DBManager.recordPatientCondition(userId, patientCondition);
		return j;
	}

	@Override
	public JsonObject assignToHospital(String userId, String hospitalId) {
		JsonObject j = DBManager.assignToHospital(userId, hospitalId);
		return j;
	}

	@Override
	public JsonObject getPatientCondition(String userId) {
		JsonObject patient = DBManager.getPatientCondition(userId);
		return patient;
	}

}
