package com.example.balletpaper.service.impl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.balletpaper.R;
import com.example.balletpaper.RegisterUserActivity;
import com.example.balletpaper.WelcomeRegisterActivity;
import com.example.balletpaper.bean.RegisterUserBean;
import com.example.balletpaper.service.RegisterUserService;
import com.example.balletpaper.sql.DB_BalletPaper;
import com.example.balletpaper.util.CommonConstants;
import com.example.balletpaper.util.UtilMethods;

public class RegisterUserServiceImpl implements RegisterUserService {

	public static Context gcontext=null;
	public RegisterUserBean gRegisterUserBean=new RegisterUserBean();
	DB_BalletPaper gDbBalletPaper;
	public LinearLayout gLinearLayoutForm, gLinearLayoutProgress;
	
	@Override
	public void callServiceRegisterUser(Context context,RegisterUserBean registerUserBean,DB_BalletPaper dbBalletPaper,
			LinearLayout linearLayoutForm,LinearLayout linearLayoutProgress) {
		gcontext=context;
		gRegisterUserBean=registerUserBean;
		gDbBalletPaper=dbBalletPaper;
		gLinearLayoutForm=linearLayoutForm;
		gLinearLayoutProgress=linearLayoutProgress;
		new SaveInformationService().execute();
		
	}
	
	private class SaveInformationService extends AsyncTask<Void, Void, Void> {
		private String Content="";

		@Override
		protected void onPreExecute() {
//			gLinearLayoutForm.setVisibility(View.GONE);
			gLinearLayoutProgress.setVisibility(View.VISIBLE);
		}

		@SuppressWarnings("deprecation")
		@Override
		protected Void doInBackground(Void... params) {
			System.out.println("onLoad!!!");
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(CommonConstants.URLService.CREATE_USER);
			post.setHeader("content-type", "application/json; charset=UTF-8");
			// Construimos el objeto cliente en formato JSON
			JSONObject dato = new JSONObject();
			try {
				dato.put(CommonConstants.RequestValueUser.EMAIL_REQUEST_USER, gRegisterUserBean.getRegisterEmail());
				dato.put(CommonConstants.RequestValueUser.PASSWORD_REQUEST_USER, UtilMethods.encriptValue(gRegisterUserBean.getRegisterPassword()));
				dato.put(CommonConstants.RequestValueUser.TYPECUSTOMER_REQUEST_USER, gRegisterUserBean.getTypeUser());
				dato.put(CommonConstants.RequestValueUser.NAMEUSER_REQUEST_USER, gRegisterUserBean.getRegisterName());
				dato.put(CommonConstants.RequestValueUser.LASTNAMEUSER_REQUEST_USER, gRegisterUserBean.getRegisterLastName());
				dato.put(CommonConstants.RequestValueUser.DNIUSER_REQUEST_USER, gRegisterUserBean.getRegisterDni());
				dato.put(CommonConstants.RequestValueUser.RECORDINGDEVICE_REQUEST_USER, gRegisterUserBean.getTypeRecording());
				
				StringEntity entity = new StringEntity(dato.toString());
				post.setEntity(entity);
				
				HttpResponse resp = httpClient.execute(post);
				String respStr = EntityUtils.toString(resp.getEntity());
				System.out.println("La respues que viene : " + respStr);
				Content = respStr;
			} catch (Exception e) {
//				gLinearLayoutForm.setVisibility(View.VISIBLE);
				gLinearLayoutProgress.setVisibility(View.GONE);
				Toast.makeText(gcontext, "Hubo un error en el proceso de Registro del Usuario ("+e.getMessage()+"). Disculpe las molestias.",Toast.LENGTH_LONG).show();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			
			JSONObject jObject = null;
			try {
				jObject = new JSONObject(Content);
				String codeResponse = jObject.getString("codeResponse");
				int idUserService = jObject.getInt("idUser");
				System.out.println("codeResponse : "+codeResponse);
				if(CommonConstants.CodeResponse.RESPONSE_SUCCESS_USER.equals(codeResponse)){
					gDbBalletPaper.insertUser(gRegisterUserBean.getRegisterEmail(), String.valueOf(idUserService));
					Intent i = new Intent(gcontext, WelcomeRegisterActivity.class);
					gcontext.startActivity(i);
				}else if(CommonConstants.CodeResponse.RESPONSE_EMAIL_EXIST.equals(codeResponse)){
					Toast.makeText(gcontext, gcontext.getString(R.string.emailExit),Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(gcontext, "Hubo un error en la respuesta del Registro de Usuario ("+e.getMessage()+"). Disculpe las molestias.",Toast.LENGTH_LONG).show();
			}
			gLinearLayoutForm.setVisibility(View.VISIBLE);
			gLinearLayoutProgress.setVisibility(View.GONE);	
		}

	}

	
}
