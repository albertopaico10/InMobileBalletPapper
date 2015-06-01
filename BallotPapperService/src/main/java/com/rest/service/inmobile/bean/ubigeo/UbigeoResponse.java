package com.rest.service.inmobile.bean.ubigeo;

import java.util.List;

public class UbigeoResponse {
	
	public String codeResponse;
	public String messagesResponse;
	public List<Ubigeo> ubigeoBean;
	
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
	public List<Ubigeo> getUbigeoBean() {
		return ubigeoBean;
	}
	public void setUbigeoBean(List<Ubigeo> ubigeoBean) {
		this.ubigeoBean = ubigeoBean;
	}
}
