package com.rest.service.inmobile.bean.complient;

public class ComplientRequest {

	public int idUser;
	public String longitude;
	public String latitude;
	public String completeAddress;
	public String commentsAdditional;
	
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getCompleteAddress() {
		return completeAddress;
	}
	public void setCompleteAddress(String completeAddress) {
		this.completeAddress = completeAddress;
	}
	public String getCommentsAdditional() {
		return commentsAdditional;
	}
	public void setCommentsAdditional(String commentsAdditional) {
		this.commentsAdditional = commentsAdditional;
	}
}
