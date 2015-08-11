package com.inmobile.ojovial.bean;

public class ComplaintBean {

	public String latitude;
	public String longitude;
	public PhotoBean photoBean;
	public String district;
	public boolean isSelectedDistrict;
	public String idUserService;
	public String alternativeAddress;
	public String comment;
	public String numberPlate;
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public PhotoBean getPhotoBean() {
		return photoBean;
	}
	public void setPhotoBean(PhotoBean photoBean) {
		this.photoBean = photoBean;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public boolean isSelectedDistrict() {
		return isSelectedDistrict;
	}
	public void setSelectedDistrict(boolean isSelectedDistrict) {
		this.isSelectedDistrict = isSelectedDistrict;
	}
	public String getIdUserService() {
		return idUserService;
	}
	public void setIdUserService(String idUserService) {
		this.idUserService = idUserService;
	}
	public String getAlternativeAddress() {
		return alternativeAddress;
	}
	public void setAlternativeAddress(String alternativeAddress) {
		this.alternativeAddress = alternativeAddress;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getNumberPlate() {
		return numberPlate;
	}
	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}
}
