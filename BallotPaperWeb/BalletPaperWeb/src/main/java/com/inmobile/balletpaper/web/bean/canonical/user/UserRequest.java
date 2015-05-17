package com.inmobile.balletpaper.web.bean.canonical.user;

public class UserRequest {
	public String email;
	public String password;
	public String typeCustomer;
	public String namesUser;
	public String lastNameUser;
	public String dniUser;
	public String recordingDevice;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTypeCustomer() {
		return typeCustomer;
	}
	public void setTypeCustomer(String typeCustomer) {
		this.typeCustomer = typeCustomer;
	}
	public String getNamesUser() {
		return namesUser;
	}
	public void setNamesUser(String namesUser) {
		this.namesUser = namesUser;
	}
	public String getLastNameUser() {
		return lastNameUser;
	}
	public void setLastNameUser(String lastNameUser) {
		this.lastNameUser = lastNameUser;
	}
	public String getDniUser() {
		return dniUser;
	}
	public void setDniUser(String dniUser) {
		this.dniUser = dniUser;
	}
	public String getRecordingDevice() {
		return recordingDevice;
	}
	public void setRecordingDevice(String recordingDevice) {
		this.recordingDevice = recordingDevice;
	}
	
}
