package com.inmobile.ojovial.util;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;

import com.inmobile.ojovial.bean.ComplaintBean;
import com.inmobile.ojovial.bean.LoginBean;
import com.inmobile.ojovial.bean.PhotoBean;
import com.inmobile.ojovial.bean.RegisterUserBean;

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
	
	
	public static PhotoBean setValuePhotoBean(String urlPhoto1, String urlPhoto2,
			String urlPhoto3) {
		PhotoBean photoBean=new PhotoBean();
		photoBean.setUrlPhoto1(urlPhoto1);
		photoBean.setUrlPhoto2(urlPhoto2);
		photoBean.setUrlPhoto3(urlPhoto3);
		return photoBean;
	}
	
	public static ComplaintBean setValueComplainBean(EditText txtNumberPlate,EditText txtComment,
			EditText txtFullAddress,ComplaintBean complainBean){
		complainBean.setNumberPlate(txtNumberPlate.getText().toString());
//		if(txtFullAddress.getText()!=null){
//			complainBean.setAlternativeAddress(txtFullAddress.getText().toString());
//		}
		if(complainBean.isSelectedDistrict()){
			complainBean.setAlternativeAddress(txtFullAddress.getText().toString());
		}
		complainBean.setComment(txtComment.getText().toString());
		return complainBean;
	}
}
