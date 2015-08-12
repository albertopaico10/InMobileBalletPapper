package com.rest.service.inmobile.bean.systemparam;


public class SystemParamResponse {
	private int id;
	private String nameParam;
	private String reasonParam;
	private int status;
	private String valueParam;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameParam() {
		return nameParam;
	}
	public void setNameParam(String nameParam) {
		this.nameParam = nameParam;
	}
	public String getReasonParam() {
		return reasonParam;
	}
	public void setReasonParam(String reasonParam) {
		this.reasonParam = reasonParam;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getValueParam() {
		return valueParam;
	}
	public void setValueParam(String valueParam) {
		this.valueParam = valueParam;
	}
}
