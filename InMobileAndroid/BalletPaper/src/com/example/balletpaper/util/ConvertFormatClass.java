package com.example.balletpaper.util;

import android.widget.EditText;

import com.example.balletpaper.bean.LoginBean;

public class ConvertFormatClass {

	public static LoginBean setValuesLoginBean(EditText emailLogin,EditText passwordLogin) {
		LoginBean loginBean=new LoginBean();
		loginBean.setEmail(emailLogin.getText().toString());
		loginBean.setPassword(passwordLogin.getText().toString());
		return loginBean;
	}
	
}
