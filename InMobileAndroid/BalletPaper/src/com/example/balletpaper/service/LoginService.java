package com.example.balletpaper.service;

import android.content.Context;

import com.example.balletpaper.bean.LoginBean;
import com.example.balletpaper.sql.DB_BalletPaper;

public interface LoginService {
	public void callServiceLogin(Context context,DB_BalletPaper dbBalletPaper,LoginBean loginBean);
}