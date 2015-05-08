package com.rest.service.inmobile.facade.impl;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.inmobile.bean.user.UserRequest;
import com.rest.service.inmobile.bean.user.UserResponse;
import com.rest.service.inmobile.facade.ReqRespManager;
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
	ReqRespManager reqRespManager;

	public UserResponse saveUserInformation(UserRequest beanRequest) {
		UserResponse beanUserResponse = new UserResponse();
		// --Save Json in Data Base
		RequestResponse valueReqResp = (RequestResponse) reqRespManager.saveOrUpdate(beanRequest,CommonConstants.TypeOperationReqResp.OPERATION_CREATE_USER,0, 0);
		System.out.println("ID Response : " + valueReqResp.getId());
		int idUser=0;
		try {
			User userDataBase=ConvertClass.convertUserRequestToDataBase(beanRequest);
			userDataBase.setStatus(1);
			idUser=userHibernate.saveUserResponseId(userDataBase);
			beanUserResponse.setIdUser(idUser);
			//--Send Email
			buidlEmailCreationUser(beanRequest.getEmail());
			//--Build Response for web service client
			beanUserResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_SUCCESS_USER);
			beanUserResponse.setMessagesResponse("The user was created successfully.");
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
		String body="<html>"
				+ "<body>"
				+ "<p>"
				+ "<b>InMobile Bienvenido - Test Email</b>"
				+ "</p><br/>"
				+ "<p>Estimo Usario:</p><br/>"
				+ "<p>Se le agradece haber elegido la aplicación</p>"
				+ "<p><b>Gracias</b></p>"
				+ "</body>"
				+ "</html>";
		MailUtil.sendEmail(emilTo,CommonConstants.Email.SUBJECT_CREATION_USER,body);
	}

}
