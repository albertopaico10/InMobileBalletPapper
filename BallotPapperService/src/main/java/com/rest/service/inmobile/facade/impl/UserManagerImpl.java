package com.rest.service.inmobile.facade.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.inmobile.bean.email.EmailBean;
import com.rest.service.inmobile.bean.user.UserRequest;
import com.rest.service.inmobile.bean.user.UserResponse;
import com.rest.service.inmobile.facade.ReqRespManager;
import com.rest.service.inmobile.facade.SystemParamManager;
import com.rest.service.inmobile.facade.UserManager;
import com.rest.service.inmobile.hibernate.UserHibernate;
import com.rest.service.inmobile.hibernate.bean.RequestResponse;
import com.rest.service.inmobile.hibernate.bean.User;
import com.rest.service.inmobile.util.CommonConstants;
import com.rest.service.inmobile.util.ConvertClass;
import com.rest.service.inmobile.util.MailUtil;


@Service
@Transactional
public class UserManagerImpl implements UserManager {
	@Autowired
	private UserHibernate userHibernate;

	@Autowired
	private ReqRespManager reqRespManager;
	
	@Autowired
	private SystemParamManager systemParamManager;

	public UserResponse saveUserInformation(UserRequest beanRequest) {
		UserResponse beanUserResponse = new UserResponse();
		// --Save Json in Data Base
		RequestResponse valueReqResp = (RequestResponse) reqRespManager.saveOrUpdate(beanRequest,CommonConstants.TypeOperationReqResp.OPERATION_CREATE_USER,0, 0);
		System.out.println("ID Response : " + valueReqResp.getId());
		int idUser=0;
		try {
			boolean validateEmail=userHibernate.existEmail(beanRequest.getEmail());
			if(!validateEmail){
				User userDataBase=ConvertClass.convertUserRequestToDataBase(beanRequest);
				userDataBase.setStatus(1);
				idUser=userHibernate.saveUserResponseId(userDataBase);
				beanUserResponse.setIdUser(idUser);
				//--Send Email
				buidlEmailCreationUser(beanRequest.getEmail());
				//--Build Response for web service client
				beanUserResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_SUCCESS_USER);
				beanUserResponse.setMessagesResponse("The user was created successfully.");
			}else{
				beanUserResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_EXITS_USER);
				beanUserResponse.setMessagesResponse("The Email exist in our Data Base");
			}
			
		} catch (Exception e) {
			beanUserResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_ERROR);
			beanUserResponse.setMessagesResponse(e.getMessage());
		}
		//--Save Json in Data Base
		reqRespManager.saveOrUpdate(beanUserResponse, 
				CommonConstants.TypeOperationReqResp.OPERATION_CREATE_USER, beanUserResponse.getIdUser(),
				valueReqResp.getId());
		return beanUserResponse;
	}
	
	public void buidlEmailCreationUser(String emilTo)throws MessagingException{
		EmailBean beanEmailBean=null;
		if(emilTo.endsWith(CommonConstants.Email.HOTMAIL_DOMAIN)){
			beanEmailBean=systemParamManager.getEmailInSystemParamGmail(CommonConstants.Email.SYSTEM_PARAM_GENERAL_EMAIL,CommonConstants.Email.TYPE_OPERATION_CREATE_USER);
			beanEmailBean.setToEmail(emilTo);
			MailUtil.sendEmail2(beanEmailBean);
		}else{
			beanEmailBean=systemParamManager.getEmailInSystemParam(CommonConstants.Email.SYSTEM_PARAM_GENERAL_EMAIL,CommonConstants.Email.TYPE_OPERATION_CREATE_USER);
			beanEmailBean.setToEmail(emilTo);
			MailUtil.sendEmail(beanEmailBean);
		}
		
	}

	public UserResponse validateUser(UserRequest userRequest) {
		System.out.println("Entreeeeee validateUser");
		UserResponse userBeanResponse=new UserResponse();
		//--Save Json in Data Base
		RequestResponse valueReqResp=(RequestResponse)reqRespManager.saveOrUpdate(userRequest, 
				CommonConstants.TypeOperationReqResp.OPERATION_VALIDATE_USER, 0,0);
		System.out.println("ID Response : "+valueReqResp.getId());
		try {
			boolean validateEmail=userHibernate.existEmail(userRequest.getEmail());
			if(validateEmail){
				User userBean=userHibernate.validateUser(userRequest.getEmail(), userRequest.getPassword());
				if(userBean!=null){
					userBeanResponse.setIdUser(userBean.getId());
					userBeanResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_SUCCESS_VALIDATION);
					userBeanResponse.setMessagesResponse("The Email was validate correctly");
					userBeanResponse.setDescription(userBean.getNamesUser()+" "+userBean.getLastNameUser());
					userBeanResponse.setAdditional(userBean.getEmail());
					userBeanResponse.setTypeUser(userBean.getTypeUser());
				}else{
					userBeanResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_FAIL_VALIDATION);
					userBeanResponse.setMessagesResponse("Password is incorrect");
					userBeanResponse.setIdUser(9999);
				}
			}else{
				userBeanResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_NOT_EXITS_USER);
				userBeanResponse.setMessagesResponse("The email don't exist ins our Data Base");
			}
		} catch (Exception e) {
			userBeanResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_ERROR);
			userBeanResponse.setMessagesResponse(e.getMessage());
		}
		//--Save Json in Data Base
		reqRespManager.saveOrUpdate(userBeanResponse, 
				CommonConstants.TypeOperationReqResp.OPERATION_CREATE_USER, userBeanResponse.getIdUser(),
				valueReqResp.getId());
		return userBeanResponse;
	}

}
