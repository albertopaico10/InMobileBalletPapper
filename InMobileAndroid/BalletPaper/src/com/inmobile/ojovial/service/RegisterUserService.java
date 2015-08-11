package com.inmobile.ojovial.service;

import com.inmobile.ojovial.bean.RegisterUserBean;
import com.inmobile.ojovial.sql.DB_BalletPaper;

import android.content.Context;
import android.widget.LinearLayout;

public interface RegisterUserService {

	public void callServiceRegisterUser(Context context,RegisterUserBean registerUserBean,DB_BalletPaper dbBalletPaper
			,LinearLayout linearLayoutForm,LinearLayout linearLayoutProgress);
	
}
