package com.rest.service.inmobile.bean.user;

public class UserResponse {
	public int idUser;
	public String codeResponse;
	public String messagesResponse;
	public String description;
	
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getCodeResponse() {
		return codeResponse;
	}
	public void setCodeResponse(String codeResponse) {
		this.codeResponse = codeResponse;
	}
	public String getMessagesResponse() {
		return messagesResponse;
	}
	public void setMessagesResponse(String messagesResponse) {
		this.messagesResponse = messagesResponse;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
