package com.rest.service.inmobile.bean.complient;

public class ComplientRequest {

	public int idUser;
	public String longitude;
	public String latitude;
	public String addressFull;
	public String commentsAdditional;
	public String numberPlate;
	public String hexPhoto1;
	public String hexPhoto2;
	public String hexPhoto3;
	public String categoryImage;
	
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
	public String getAddressFull() {
		return addressFull;
	}
	public void setAddressFull(String addressFull) {
		this.addressFull = addressFull;
	}
	public String getCommentsAdditional() {
		return commentsAdditional;
	}
	public void setCommentsAdditional(String commentsAdditional) {
		this.commentsAdditional = commentsAdditional;
	}
	public String getNumberPlate() {
		return numberPlate;
	}
	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}
	public String getHexPhoto1() {
		return hexPhoto1;
	}
	public void setHexPhoto1(String hexPhoto1) {
		this.hexPhoto1 = hexPhoto1;
	}
	public String getHexPhoto2() {
		return hexPhoto2;
	}
	public void setHexPhoto2(String hexPhoto2) {
		this.hexPhoto2 = hexPhoto2;
	}
	public String getHexPhoto3() {
		return hexPhoto3;
	}
	public void setHexPhoto3(String hexPhoto3) {
		this.hexPhoto3 = hexPhoto3;
	}
	public String getCategoryImage() {
		return categoryImage;
	}
	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}
}
