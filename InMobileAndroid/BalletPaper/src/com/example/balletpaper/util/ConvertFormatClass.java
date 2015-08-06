package com.example.balletpaper.util;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.balletpaper.bean.LoginBean;
import com.example.balletpaper.bean.RegisterUserBean;

public class ConvertFormatClass {

	public static LoginBean setValuesLoginBean(EditText emailLogin,EditText passwordLogin) {
		LoginBean loginBean=new LoginBean();
		loginBean.setEmail(emailLogin.getText().toString());
		loginBean.setPassword(passwordLogin.getText().toString());
		return loginBean;
	}
	
	public static RegisterUserBean setValuesRegisterUserBean(EditText registerEmail,EditText registerpassword
			,EditText registerName,EditText registerLastName
			,EditText registerDni,String typeUser,String typeRecording){
		RegisterUserBean registerUserBean=new RegisterUserBean();
		registerUserBean.setRegisterEmail(registerEmail.getText().toString());
		registerUserBean.setRegisterPassword(registerpassword.getText().toString());
		registerUserBean.setRegisterName(registerName.getText().toString());
		registerUserBean.setRegisterLastName(registerLastName.getText().toString());
		registerUserBean.setRegisterDni(registerDni.getText().toString());
		registerUserBean.setTypeUser(typeUser);
		registerUserBean.setTypeRecording(typeRecording);
		return registerUserBean;
	}
	
}
