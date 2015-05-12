package com.rest.service.inmobile.util;

import com.rest.service.inmobile.bean.user.UserRequest;
import com.rest.service.inmobile.hibernate.bean.User;

public class ConvertClass {

	public static User convertUserRequestToDataBase(UserRequest beanRequest){
		User userDataBase=new User();
		userDataBase.setEmail(beanRequest.getEmail());
		userDataBase.setPasswordUser(beanRequest.getPassword());
		userDataBase.setTypeUser(Integer.parseInt(beanRequest.getTypeCustomer()));
		userDataBase.setNamesUser(beanRequest.getNamesUser());
		userDataBase.setLastNameUser(beanRequest.getLastNameUser());
		userDataBase.setDniUser(beanRequest.getDniUser());
		userDataBase.setRecordingDevice(beanRequest.getRecordingDevice());
		return userDataBase;
	}
}
