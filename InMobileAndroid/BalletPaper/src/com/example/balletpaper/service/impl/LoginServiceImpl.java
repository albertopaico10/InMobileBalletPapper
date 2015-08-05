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
import android.widget.Toast;

import com.example.balletpaper.LoginActivity;
import com.example.balletpaper.PrincipalMainActivity;
import com.example.balletpaper.R;
import com.example.balletpaper.bean.LoginBean;
import com.example.balletpaper.service.LoginService;
import com.example.balletpaper.sql.DB_BalletPaper;
import com.example.balletpaper.util.CommonConstants;
import com.example.balletpaper.util.UtilMethods;

public class LoginServiceImpl implements LoginService {

	public static Context gcontext=null;
	DB_BalletPaper gDbBalletPaper;
	boolean isCorrect=false;
	public LoginBean gloginBean=new LoginBean();
	
	
	public void callServiceLogin(Context context,DB_BalletPaper dbBalletPaper,LoginBean loginBean){
		gcontext=context;
		gDbBalletPaper=dbBalletPaper;
		gloginBean=loginBean;
		new LoginValidateService().execute();	
	}
	
	private class LoginValidateService extends AsyncTask<Void, Void, Void> {
		private ProgressDialog dialog = new ProgressDialog(gcontext);
		private String Content="";
		@Override
		protected void onPreExecute() {
			dialog.setMessage(gcontext.getString(R.string.watingLogin));
			dialog.show();
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
				dialog.dismiss();
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
					gDbBalletPaper.updateUserDesactive();
//					boolean exitUser=dbBalletPaper.existIdUserService(String.valueOf(idUser));
//					if(exitUser){
//						System.out.println("Tendria que insertarlo no existe en esta Base de Datos");
//						Toast.makeText(LoginActivity.this, "Se va insertar el usuario en SQL LITE "+idUser+"***"+exitUser,Toast.LENGTH_LONG).show();
//						dbBalletPaper.insertUser(email, String.valueOf(idUser));	
//					}
					gDbBalletPaper.insertUser(email, String.valueOf(idUser));	
					gDbBalletPaper.updateUserActive(String.valueOf(idUser));
					
					Intent i = new Intent(gcontext, PrincipalMainActivity.class);
					gcontext.startActivity(i);
				}else if(CommonConstants.CodeResponse.RESPONSE_FAIL_VALIDATION.equals(codeResponse)){
					Toast.makeText(gcontext, gcontext.getString(R.string.validationFail),Toast.LENGTH_LONG).show();
				}else if(CommonConstants.CodeResponse.RESPONSE_EMAIL_NOT_EXIST.equals(codeResponse)){
					Toast.makeText(gcontext, gcontext.getString(R.string.emailNotExit),Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(gcontext, "Hubo un error en la respuesta del Registro de Usuario ("+e.getMessage()+"). Disculpe las molestias.",Toast.LENGTH_LONG).show();
			}
			dialog.dismiss();
		}
		
	}
	
}
