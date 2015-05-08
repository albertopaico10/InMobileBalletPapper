package com.rest.service.inmobile.facade;

import org.springframework.stereotype.Service;

import com.rest.service.inmobile.bean.user.UserRequest;
import com.rest.service.inmobile.bean.user.UserResponse;

@Service
public interface UserManager {
	
	public UserResponse saveUserInformation(UserRequest beanRequest);
	
}
