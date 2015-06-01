package com.rest.service.inmobile.bean.image;

public class ImageRequest {

	public String categoryImage;
	public String hexImage;
	public int idUser;
	public int idComplient;
	
	public int idImage;
	public String rootProject;
	
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
	public int getIdImage() {
		return idImage;
	}
	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}
	public String getRootProject() {
		return rootProject;
	}
	public void setRootProject(String rootProject) {
		this.rootProject = rootProject;
	}
	
}
