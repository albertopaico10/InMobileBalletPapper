package com.inmobile.ojovial.service.impl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.inmobile.ojovial.PrincipalMainActivity;
import com.inmobile.ojovial.R;
import com.inmobile.ojovial.bean.LoginBean;
import com.inmobile.ojovial.service.LoginService;
import com.inmobile.ojovial.sql.DB_BalletPaper;
import com.inmobile.ojovial.util.CommonConstants;
import com.inmobile.ojovial.util.UtilMethods;

public class LoginServiceImpl implements LoginService {

	public static Context gcontext=null;
	DB_BalletPaper gDbBalletPaper;
	boolean isCorrect=false;
	public LoginBean gloginBean=new LoginBean();
	public LinearLayout gLinearLayoutForm,gLinearLayoutProgress;

	public void callServiceLogin(Context context,DB_BalletPaper dbBalletPaper,LoginBean loginBean,
			LinearLayout linearLayoutForm,LinearLayout linearLayoutProgress){
		gcontext=context;
		gDbBalletPaper=dbBalletPaper;
		gloginBean=loginBean;
		gLinearLayoutForm=linearLayoutForm;
		gLinearLayoutProgress=linearLayoutProgress;
		new LoginValidateService().execute();	
	}
	
	private class LoginValidateService extends AsyncTask<Void, Void, Void> {
		private String Content="";
		@Override
		protected void onPreExecute() {
			gLinearLayoutProgress.setVisibility(View.VISIBLE);
		}
		@SuppressWarnings("deprecation")
		@Override
		protected Void doInBackground(Void... params) {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(CommonConstants.URLService.VALIDATION_USER);
			post.setHeader("content-type", "application/json; charset=UTF-8");
			JSONObject dato = new JSONObject();
			try {
				dato.put(CommonConstants.RequestLoginUser.EMAIL_REQUEST_USER, gloginBean.getEmail());
				dato.put(CommonConstants.RequestLoginUser.PASSWORD_REQUEST_USER, UtilMethods.encriptValue(gloginBean.getPassword()));
				StringEntity entity = new StringEntity(dato.toString());
				post.setEntity(entity);
				
				HttpResponse resp = httpClient.execute(post);
				String respStr = EntityUtils.toString(resp.getEntity());
				System.out.println("La respues que viene : " + respStr);
				Content = respStr;
			} catch (Exception e) {
				gLinearLayoutForm.setVisibility(View.VISIBLE);
				gLinearLayoutProgress.setVisibility(View.GONE);	
				Toast.makeText(gcontext, "Hubo un error en el proceso de Registro de Usuario ("+e.getMessage()+"). Disculpe las molestias.",Toast.LENGTH_LONG).show();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// Close progress dialog
			JSONObject jObject = null;
			try {
				jObject = new JSONObject(Content);
				String codeResponse = jObject.getString("codeResponse");
				String email = jObject.getString("additional");
				int idUser = jObject.getInt("idUser");
				System.out.println("codeResponse : "+codeResponse+"************++");
				if(CommonConstants.CodeResponse.RESPONSE_SUCCESS_VALIDATION.equals(codeResponse)){
					boolean exitUser=gDbBalletPaper.existIdUserService(String.valueOf(idUser));
					if(!exitUser){
						gDbBalletPaper.updateUserDesactive();
						gDbBalletPaper.insertUser(email, String.valueOf(idUser));
					}
					gDbBalletPaper.updateUserActive(String.valueOf(idUser));
					Intent i = new Intent(gcontext, PrincipalMainActivity.class);
					gcontext.startActivity(i);
				}else if(CommonConstants.CodeResponse.RESPONSE_FAIL_VALIDATION.equals(codeResponse)){
					gLinearLayoutForm.setVisibility(View.VISIBLE);
					gLinearLayoutProgress.setVisibility(View.GONE);	
					Toast.makeText(gcontext, gcontext.getString(R.string.validationFail),Toast.LENGTH_LONG).show();
				}else if(CommonConstants.CodeResponse.RESPONSE_EMAIL_NOT_EXIST.equals(codeResponse)){
					gLinearLayoutForm.setVisibility(View.VISIBLE);
					gLinearLayoutProgress.setVisibility(View.GONE);	
					Toast.makeText(gcontext, gcontext.getString(R.string.emailNotExit),Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(gcontext, "Hubo un error en la respuesta del Registro de Usuario ("+e.getMessage()+"). Disculpe las molestias.",Toast.LENGTH_LONG).show();
			}
			gLinearLayoutForm.setVisibility(View.VISIBLE);
			gLinearLayoutProgress.setVisibility(View.GONE);	
		}
		
	}
	
}
