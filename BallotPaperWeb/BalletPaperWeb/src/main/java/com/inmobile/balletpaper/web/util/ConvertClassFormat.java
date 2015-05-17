package com.inmobile.balletpaper.web.util;

import com.inmobile.balletpaper.web.bean.RegisterUserDTO;
import com.inmobile.balletpaper.web.bean.canonical.user.UserRequest;

public class ConvertClassFormat {
	
	public static UserRequest convertWebToServiceUser(RegisterUserDTO registerUser){
		UserRequest userRequest=new UserRequest();
		userRequest.setEmail(registerUser.getUser());
		userRequest.setPassword(UtilMethods.encriptValue(registerUser.getPassword()));
		userRequest.setTypeCustomer("USER_WEB");
		return userRequest;
	}
}
