package com.rest.service.inmobile.bean.image;

import java.util.List;

public class ListImageResponse {
	
	public String codeResponse;
	public String messageResponse;
	
	public List<ImageResponse> listImageResponse;
	
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
	public List<ImageResponse> getListImageResponse() {
		return listImageResponse;
	}
	public void setListImageResponse(List<ImageResponse> listImageResponse) {
		this.listImageResponse = listImageResponse;
	}
}
