package com.example.balletpaper.service;

import com.example.balletpaper.bean.RegisterUserBean;
import com.example.balletpaper.sql.DB_BalletPaper;

import android.content.Context;
import android.widget.LinearLayout;

public interface RegisterUserService {

	public void callServiceRegisterUser(Context context,RegisterUserBean registerUserBean,DB_BalletPaper dbBalletPaper
			,LinearLayout linearLayoutForm,LinearLayout linearLayoutProgress);
	
}
