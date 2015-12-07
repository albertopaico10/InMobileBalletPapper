package com.rest.service.inmobile.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.inmobile.bean.systemparam.SystemParamResponse;
import com.rest.service.inmobile.facade.SystemParamManager;
import com.rest.service.inmobile.facade.UtilManager;
import com.rest.service.inmobile.util.CommonConstants;

@Service
@Transactional
public class UtilManagerImpl implements UtilManager{

	@Autowired
	private SystemParamManager systemParamManager;
	
	public boolean isSendEmailFromOtherAccount(String email){
		boolean value=false;
		List<SystemParamResponse> listSystemParamResponse=systemParamManager.listSpecificSystemParam(CommonConstants.SystemParam.SYSTEM_PARAM_SEND_EMAIL);
		for(SystemParamResponse beanSystemParamResponse:listSystemParamResponse){
			if(email.endsWith(beanSystemParamResponse.getValueParam())){
				value=true;
				break;
			}
		}
		return value;
	}
	
}
