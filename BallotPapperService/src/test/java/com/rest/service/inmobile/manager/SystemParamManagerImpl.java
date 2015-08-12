package com.rest.service.inmobile.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.inmobile.bean.email.EmailBean;
import com.rest.service.inmobile.facade.SystemParamManager;
import com.rest.service.inmobile.util.CommonConstants;

@ContextConfiguration(locations = {"/dispatcher-servlet.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SystemParamManagerImpl {
	
	@Autowired
	private SystemParamManager systemParamManager;
	
	@Test
	public void getEmailInformation()throws Exception{
		EmailBean emailBean=systemParamManager.getEmailInSystemParam(CommonConstants.Email.SYSTEM_PARAM_GENERAL_EMAIL,CommonConstants.Email.TYPE_OPERATION_CREATE_USER);
		System.out.println("Information : ");
		System.out.println("* "+emailBean.getEmailFrom());
		System.out.println("* "+emailBean.getEmailPort());
		System.out.println("* "+emailBean.getEmailSmtp());
		System.out.println("* "+emailBean.getEmailTrue());
		System.out.println("* "+emailBean.getPasswordFrom());
		System.out.println("* "+emailBean.getSubjectEmail());
		System.out.println("* "+emailBean.getBodyEmail());
		
		EmailBean emailBean2=systemParamManager.getEmailInSystemParam(CommonConstants.Email.SYSTEM_PARAM_GENERAL_EMAIL,CommonConstants.Email.TYPE_OPERATION_REGISTER_COMPLAINT);
		System.out.println("Information : ");
		System.out.println("* "+emailBean2.getEmailFrom());
		System.out.println("* "+emailBean2.getEmailPort());
		System.out.println("* "+emailBean2.getEmailSmtp());
		System.out.println("* "+emailBean2.getEmailTrue());
		System.out.println("* "+emailBean2.getPasswordFrom());
		System.out.println("* "+emailBean2.getSubjectEmail());
		System.out.println("* "+emailBean2.getBodyEmail());
	}
}
