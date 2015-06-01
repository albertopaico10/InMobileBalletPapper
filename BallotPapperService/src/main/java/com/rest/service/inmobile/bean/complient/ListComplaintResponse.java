package com.rest.service.inmobile.bean.complient;

import java.util.List;

public class ListComplaintResponse {
	public String codeResponse;
	public String messageResponse;
	public List<ComplientResponse> listComplaintResponse;
	public List<TypeComplaintResponse> listTypeComplaint;
	
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
	public List<ComplientResponse> getListComplaintResponse() {
		return listComplaintResponse;
	}
	public void setListComplaintResponse(
			List<ComplientResponse> listComplaintResponse) {
		this.listComplaintResponse = listComplaintResponse;
	}
	public List<TypeComplaintResponse> getListTypeComplaint() {
		return listTypeComplaint;
	}
	public void setListTypeComplaint(List<TypeComplaintResponse> listTypeComplaint) {
		this.listTypeComplaint = listTypeComplaint;
	}
	
}
