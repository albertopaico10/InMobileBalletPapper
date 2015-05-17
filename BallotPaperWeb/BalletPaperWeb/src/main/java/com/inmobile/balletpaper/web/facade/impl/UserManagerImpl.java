package com.inmobile.balletpaper.web.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inmobile.balletpaper.web.bean.RegisterUserDTO;
import com.inmobile.balletpaper.web.bean.ReturnService;
import com.inmobile.balletpaper.web.bean.canonical.user.UserRequest;
import com.inmobile.balletpaper.web.bean.canonical.user.UserResponse;
import com.inmobile.balletpaper.web.facade.UserManager;
import com.inmobile.balletpaper.web.util.CommonConstants;
import com.inmobile.balletpaper.web.util.ConvertClassFormat;
import com.inmobile.balletpaper.web.util.UtilMethods;

@Service
public class UserManagerImpl implements UserManager{

	public ReturnService validateUser(RegisterUserDTO beanUser) {
		ReturnService beanReturn=new ReturnService();
		RestTemplate restTemplate=new RestTemplate();
		UserRequest beanRequest=ConvertClassFormat.convertWebToServiceUser(beanUser);
		System.out.println("Valor Request : "+UtilMethods.fromObjectToString(beanRequest));
		System.out.println("URL : "+CommonConstants.URLService.URL_VALIDATION_USER);
		UserResponse beanResponse=restTemplate.postForObject(CommonConstants.URLService.URL_VALIDATION_USER, beanRequest, UserResponse.class);
		System.out.println("Valor Response : "+UtilMethods.fromObjectToString(beanResponse));
		if(CommonConstants.Response.RESPONSE_SUCCESS_VALIDATION.equals(beanResponse.getCodeResponse())){
			beanReturn.setReturnPage(CommonConstants.Page.REDIRECT_WELCOME_PAGE);
			beanReturn.setSpecificMessages(beanResponse.getDescription());
		}else if(CommonConstants.Response.RESPONSE_FAIL_VALIDATION.equals(beanResponse.getCodeResponse()) ||
				CommonConstants.Response.RESPONSE_NOT_EXITS_USER.equals(beanResponse.getCodeResponse())){
			beanReturn.setReturnPage(CommonConstants.Page.REDIRECT_LOGIN_PAGE);
		}
		beanReturn.setMessages(beanResponse.getCodeResponse());
		beanReturn.setIdUser(beanResponse.getIdUser());
		return beanReturn;
	}

}
