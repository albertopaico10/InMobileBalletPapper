package com.inmobile.ojovial.service;

import com.inmobile.ojovial.bean.RegisterUserBean;
import com.inmobile.ojovial.sql.DB_BalletPaper;

import android.content.Context;
import android.widget.LinearLayout;

public interface RegisterUserService {
            
	public void sucessUserRegister(String email,String idUserService,DB_BalletPaper dbBalletPaper);
	public String callRegisterUserService(RegisterUserBean registerUserBean)throws Exception;
	
}
