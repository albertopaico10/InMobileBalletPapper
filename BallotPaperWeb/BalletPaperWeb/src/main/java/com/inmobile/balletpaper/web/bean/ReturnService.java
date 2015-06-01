package com.inmobile.balletpaper.web.bean;

import java.util.List;

import com.inmobile.balletpaper.web.bean.canonical.balletpaper.BalletPaperResponse;

public class ReturnService {
	public String returnPage;
	public String messages;
	public String specificMessages;
	public int idUser;
	public int typeUser;
	
	public List<BalletPaperDTO> listBalletPapper;
	
	public String getReturnPage() {
		return returnPage;
	}
	public void setReturnPage(String returnPage) {
		this.returnPage = returnPage;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public String getSpecificMessages() {
		return specificMessages;
	}
	public void setSpecificMessages(String specificMessages) {
		this.specificMessages = specificMessages;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public List<BalletPaperDTO> getListBalletPapper() {
		return listBalletPapper;
	}
	public void setListBalletPapper(List<BalletPaperDTO> listBalletPapper) {
		this.listBalletPapper = listBalletPapper;
	}
	public int getTypeUser() {
		return typeUser;
	}
	public void setTypeUser(int typeUser) {
		this.typeUser = typeUser;
	}
}
