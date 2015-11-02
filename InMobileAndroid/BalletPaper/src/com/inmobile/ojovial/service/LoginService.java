package com.inmobile.ojovial.service;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.inmobile.ojovial.bean.LoginBean;
import com.inmobile.ojovial.sql.DB_BalletPaper;

public interface LoginService {
	public void sucessLogin(String idUser,String email)throws Exception;
	public String callService(LoginBean loginBean)throws Exception;
}
