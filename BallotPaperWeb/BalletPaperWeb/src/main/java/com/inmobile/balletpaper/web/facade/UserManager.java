package com.inmobile.balletpaper.web.facade;

import org.springframework.stereotype.Service;

import com.inmobile.balletpaper.web.bean.RegisterUserDTO;
import com.inmobile.balletpaper.web.bean.ReturnService;

@Service
public interface UserManager {
	public ReturnService validateUser(RegisterUserDTO beanUser);
}
