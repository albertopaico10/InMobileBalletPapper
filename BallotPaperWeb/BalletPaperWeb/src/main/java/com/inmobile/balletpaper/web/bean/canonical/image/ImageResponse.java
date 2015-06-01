package com.inmobile.balletpaper.web.bean.canonical.image;

public class ImageResponse {

	public int idImage;
	public String codeResponse;
	public String messageResponse;
	public String nameFileImage;
	public byte[] byteImage;
	public String rootImage;
	
	public int getIdImage() {
		return idImage;
	}
	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}
	public String getCodeResponse() {
		return codeResponse;
	}
	public void setCodeResponse(String codeResponse) {
		this.codeResponse = codeResponse;
	}
	public String getMessageResponse() {
		return messageResponse;
	}
	public void setMessageResponse(String messageResponse) {
		this.messageResponse = messageResponse;
	}
	public byte[] getByteImage() {
		return byteImage;
	}
	public void setByteImage(byte[] byteImage) {
		this.byteImage = byteImage;
	}
	public String getNameFileImage() {
		return nameFileImage;
	}
	public void setNameFileImage(String nameFileImage) {
		this.nameFileImage = nameFileImage;
	}
	public String getRootImage() {
		return rootImage;
	}
	public void setRootImage(String rootImage) {
		this.rootImage = rootImage;
	}
}

