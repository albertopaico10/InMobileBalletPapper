package com.rest.service.inmobile.facade;

import java.util.List;

import com.rest.service.inmobile.bean.email.EmailBean;
import com.rest.service.inmobile.bean.systemparam.SystemParamResponse;

public interface SystemParamManager {

	public List<SystemParamResponse> listSpecificSystemParam(List<String> listValue);
	public EmailBean getEmailInSystemParam(String generalParam,String typeOperation);
	public EmailBean getEmailInSystemParamGmail(String generalParam,String typeOperation);
}
