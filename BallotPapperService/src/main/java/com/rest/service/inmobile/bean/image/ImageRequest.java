package com.rest.service.inmobile.bean.image;

public class ImageRequest {

	public String categoryImage;
	public String hexImage;
	public int idUser;
	public int idComplient;
	
	
	public String getCategoryImage() {
		return categoryImage;
	}
	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}
	public String getHexImage() {
		return hexImage;
	}
	public void setHexImage(String hexImage) {
		this.hexImage = hexImage;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdComplient() {
		return idComplient;
	}
	public void setIdComplient(int idComplient) {
		this.idComplient = idComplient;
	}
}
