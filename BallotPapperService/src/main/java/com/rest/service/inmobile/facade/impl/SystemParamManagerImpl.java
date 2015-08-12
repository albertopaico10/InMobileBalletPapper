package com.rest.service.inmobile.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.inmobile.bean.email.EmailBean;
import com.rest.service.inmobile.bean.systemparam.SystemParamResponse;
import com.rest.service.inmobile.facade.SystemParamManager;
import com.rest.service.inmobile.hibernate.SystemParamHibernate;
import com.rest.service.inmobile.hibernate.bean.SystemParam;
import com.rest.service.inmobile.util.CommonConstants;
import com.rest.service.inmobile.util.ConvertClass;

@Service
@Transactional
public class SystemParamManagerImpl implements SystemParamManager {

	@Autowired
	public SystemParamHibernate systemParamHibernate;
	
	public List<SystemParamResponse> listSpecificSystemParam(List<String> listValue) {
		List<SystemParamResponse> listSystemParamResponse=new ArrayList<SystemParamResponse>();
		List<SystemParam> listSystemParam=systemParamHibernate.listsSpecificSystemParam(listValue);
		for(SystemParam beanSystemParam:listSystemParam){
			SystemParamResponse beanSystemParamResponse=ConvertClass.convertFromDataBaseToSystemParamResponse(beanSystemParam);
			listSystemParamResponse.add(beanSystemParamResponse);
		}
		return listSystemParamResponse;
	}

	public EmailBean getEmailInformation(List<String> listValue) {
		EmailBean beanEmail=new EmailBean();
		List<SystemParam> listSystemParam=systemParamHibernate.listsSpecificSystemParam(listValue);
		for(SystemParam beanSystemParam:listSystemParam){
			if(CommonConstants.Email.SYSTEM_PARAM_EMAIL_FROM.equals(beanSystemParam.getValueParam())){
				beanEmail.setEmailFrom(beanSystemParam.getValueParam());
			}else if(CommonConstants.Email.SYSTEM_PARAM_EMAIL_PORT_GMAIL.equals(beanSystemParam.getValueParam())){
				beanEmail.setEmailPort(beanSystemParam.getValueParam());
			}else if(CommonConstants.Email.SYSTEM_PARAM_EMAIL_SMTP_GMAIL.equals(beanSystemParam.getValueParam())){
				beanEmail.setEmailSmtp(beanSystemParam.getValueParam());
			}else if(CommonConstants.Email.SYSTEM_PARAM_EMAIL_TRUE.equals(beanSystemParam.getValueParam())){
				beanEmail.setEmailTrue(beanSystemParam.getValueParam());
			}else if(CommonConstants.Email.SYSTEM_PARAM_PASSWORD_FROM.equals(beanSystemParam.getValueParam())){
				beanEmail.setPasswordFrom(beanSystemParam.getValueParam());
			}else if(CommonConstants.Email.SYSTEM_PARAM_SUBJECT_COMPLETE_COMPLAINT.equals(beanSystemParam.getValueParam())){
				beanEmail.setSubjectEmail(beanSystemParam.getValueParam());
			}else if(CommonConstants.Email.SYSTEM_PARAM_SUBJECT_CREATION_USER.equals(beanSystemParam.getValueParam())){
				beanEmail.setSubjectEmail(beanSystemParam.getValueParam());
			}else if(CommonConstants.Email.SYSTEM_PARAM_BODY_EMAIL_CREATION_USER.equals(beanSystemParam.getValueParam())){
				beanEmail.setBodyEmail(beanSystemParam.getValueParam());
			}
		}
		return beanEmail;
	}
	
	public EmailBean getEmailInSystemParam(String generalParam) {
		EmailBean beanEmail=new EmailBean();
		List<SystemParam> listSystemParam=systemParamHibernate.listsByParam(generalParam);
		for(SystemParam beanSystemParam:listSystemParam){
			if(CommonConstants.Email.SYSTEM_PARAM_EMAIL_FROM.equals(beanSystemParam.getNameParam())){
				beanEmail.setEmailFrom(beanSystemParam.getValueParam());
			}else if(CommonConstants.Email.SYSTEM_PARAM_EMAIL_PORT_GMAIL.equals(beanSystemParam.getNameParam())){
				beanEmail.setEmailPort(beanSystemParam.getValueParam());
			}else if(CommonConstants.Email.SYSTEM_PARAM_EMAIL_SMTP_GMAIL.equals(beanSystemParam.getNameParam())){
				beanEmail.setEmailSmtp(beanSystemParam.getValueParam());
			}else if(CommonConstants.Email.SYSTEM_PARAM_EMAIL_TRUE.equals(beanSystemParam.getNameParam())){
				beanEmail.setEmailTrue(beanSystemParam.getValueParam());
			}else if(CommonConstants.Email.SYSTEM_PARAM_PASSWORD_FROM.equals(beanSystemParam.getNameParam())){
				beanEmail.setPasswordFrom(beanSystemParam.getValueParam());
			}else if(CommonConstants.Email.SYSTEM_PARAM_SUBJECT_COMPLETE_COMPLAINT.equals(beanSystemParam.getNameParam())){
				beanEmail.setSubjectEmail(beanSystemParam.getValueParam());
			}else if(CommonConstants.Email.SYSTEM_PARAM_SUBJECT_CREATION_USER.equals(beanSystemParam.getNameParam())){
				beanEmail.setSubjectEmail(beanSystemParam.getValueParam());
			}else if(CommonConstants.Email.SYSTEM_PARAM_BODY_EMAIL_CREATION_USER.equals(beanSystemParam.getNameParam())){
				beanEmail.setBodyEmail(beanSystemParam.getValueParam());
			}
		}
		return beanEmail;
	}

}
