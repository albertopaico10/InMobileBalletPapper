package com.inmobile.balletpaper.web.bean.canonical.balletpaper;

import java.util.List;


public class ListBalletPaperResponse {
	public String codeResponse;
	public String messageResponse;
	public List<BalletPaperResponse> listComplaintResponse;
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
	public List<BalletPaperResponse> getListComplaintResponse() {
		return listComplaintResponse;
	}
	public void setListComplaintResponse(
			List<BalletPaperResponse> listComplaintResponse) {
		this.listComplaintResponse = listComplaintResponse;
	}
	public List<TypeComplaintResponse> getListTypeComplaint() {
		return listTypeComplaint;
	}
	public void setListTypeComplaint(List<TypeComplaintResponse> listTypeComplaint) {
		this.listTypeComplaint = listTypeComplaint;
	}
}
