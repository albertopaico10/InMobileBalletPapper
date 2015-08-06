package com.example.balletpaper.service;

import com.example.balletpaper.bean.RegisterUserBean;
import com.example.balletpaper.sql.DB_BalletPaper;

import android.content.Context;

public interface RegisterUserService {

	public void callServiceRegisterUser(Context context,RegisterUserBean registerUserBean,DB_BalletPaper dbBalletPaper);
	
}
