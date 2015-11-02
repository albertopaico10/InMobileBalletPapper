package com.inmobile.ojovial.service.impl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.inmobile.ojovial.bean.RegisterUserBean;
import com.inmobile.ojovial.service.RegisterUserService;
import com.inmobile.ojovial.sql.DB_BalletPaper;
import com.inmobile.ojovial.util.CommonConstants;
import com.inmobile.ojovial.util.UtilMethods;

public class RegisterUserServiceImpl implements RegisterUserService {

	DB_BalletPaper gDbBalletPaper;
	
	public void sucessUserRegister(String email,String idUserService){
		gDbBalletPaper.insertUser(email, idUserService);
	}
	
	public String callRegisterUserService(RegisterUserBean registerUserBean)throws Exception{
		String respStr="";
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(CommonConstants.URLService.CREATE_USER);
		post.setHeader("content-type", "application/json; charset=UTF-8");
		// Construimos el objeto cliente en formato JSON
		JSONObject dato = new JSONObject();
		dato.put(CommonConstants.RequestValueUser.EMAIL_REQUEST_USER, registerUserBean.getRegisterEmail());
		dato.put(CommonConstants.RequestValueUser.PASSWORD_REQUEST_USER, UtilMethods.encriptValue(registerUserBean.getRegisterPassword()));
		dato.put(CommonConstants.RequestValueUser.TYPECUSTOMER_REQUEST_USER, registerUserBean.getTypeUser());
		dato.put(CommonConstants.RequestValueUser.NAMEUSER_REQUEST_USER, registerUserBean.getRegisterName());
		dato.put(CommonConstants.RequestValueUser.LASTNAMEUSER_REQUEST_USER, registerUserBean.getRegisterLastName());
		dato.put(CommonConstants.RequestValueUser.DNIUSER_REQUEST_USER, registerUserBean.getRegisterDni());
		dato.put(CommonConstants.RequestValueUser.RECORDINGDEVICE_REQUEST_USER, registerUserBean.getTypeRecording());
		
		StringEntity entity = new StringEntity(dato.toString());
		post.setEntity(entity);
		
		HttpResponse resp = httpClient.execute(post);
		respStr = EntityUtils.toString(resp.getEntity());
		System.out.println("La respues que viene : " + respStr);
		return respStr;
	}
	
}
