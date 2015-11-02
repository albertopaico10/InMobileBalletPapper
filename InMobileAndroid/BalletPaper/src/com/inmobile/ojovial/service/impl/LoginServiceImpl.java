package com.inmobile.ojovial.service.impl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.inmobile.ojovial.bean.LoginBean;
import com.inmobile.ojovial.service.LoginService;
import com.inmobile.ojovial.sql.DB_BalletPaper;
import com.inmobile.ojovial.util.CommonConstants;
import com.inmobile.ojovial.util.UtilMethods;

public class LoginServiceImpl implements LoginService {

	DB_BalletPaper gDbBalletPaper;

	public void sucessLogin(String idUser,String email)throws Exception{
		boolean exitUser=gDbBalletPaper.existIdUserService(idUser);
		gDbBalletPaper.updateUserDesactive();
		if(exitUser){
			gDbBalletPaper.insertUser(email, idUser);
		}
		gDbBalletPaper.updateUserActive(idUser);
	}
	
	public String callService(LoginBean loginBean)throws Exception{
		System.out.println("Email 222: "+loginBean.getEmail()+"***"+loginBean.getPassword());
		System.out.println("Common : "+CommonConstants.URLService.VALIDATION_USER+"**** "+CommonConstants.RequestLoginUser.EMAIL_REQUEST_USER+
				"****"+CommonConstants.RequestLoginUser.PASSWORD_REQUEST_USER);
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(CommonConstants.URLService.VALIDATION_USER);
		post.setHeader("content-type", "application/json; charset=UTF-8");
		JSONObject dato = new JSONObject();
		dato.put(CommonConstants.RequestLoginUser.EMAIL_REQUEST_USER, loginBean.getEmail());
		dato.put(CommonConstants.RequestLoginUser.PASSWORD_REQUEST_USER, UtilMethods.encriptValue(loginBean.getPassword()));
		StringEntity entity = new StringEntity(dato.toString());
		post.setEntity(entity);
			
		HttpResponse resp = httpClient.execute(post);
		String respStr = EntityUtils.toString(resp.getEntity());
		System.out.println("La respues que viene : " + respStr);
		return respStr;
	}
	
	
}
