package com.rest.service.inmobile.bean.complient;

public class ComplientRequest {

	public int idUser;
	public String longitude;
	public String latitude;
	public String addressFull;
	public String commentsAdditional;
	public String numberPlate;
	private byte[] fileImage1;
	private byte[] fileImage2;
	private byte[] fileImage3;
	public String categoryImage;
	public String district;
	public String address;
	public String country;
	
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
	public String getCategoryImage() {
		return categoryImage;
	}
	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public byte[] getFileImage1() {
		return fileImage1;
	}
	public void setFileImage1(byte[] fileImage1) {
		this.fileImage1 = fileImage1;
	}
	public byte[] getFileImage2() {
		return fileImage2;
	}
	public void setFileImage2(byte[] fileImage2) {
		this.fileImage2 = fileImage2;
	}
	public byte[] getFileImage3() {
		return fileImage3;
	}
	public void setFileImage3(byte[] fileImage3) {
		this.fileImage3 = fileImage3;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
