package com.example.balletpaper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.example.balletpaper.sql.DB_BalletPaper;
import com.example.balletpaper.util.CommonConstants;
import com.example.balletpaper.util.UtilMethods;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity{

	private EditText emailLogin;
	private EditText passwordLogin;
	private TextView txtLnkRegisterUser;
	private DB_BalletPaper dbBalletPaper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginnew2);
		emailLogin=(EditText)findViewById(R.id.idLoginEmail);
		passwordLogin=(EditText)findViewById(R.id.idLoginPassword);
		txtLnkRegisterUser=(TextView)findViewById(R.id.link_to_register);
		
		createAndroidDatase();
	}
	
	private void createAndroidDatase() {
		dbBalletPaper = new DB_BalletPaper(this, "DB_AndroidBalletPaper", null, 1);
	}
	
	public void onOkClick(View v){
		boolean validateField=validateAllField();
		if(validateField){
			new LoginValidateService().execute();
		}
	}
	
	private class LoginValidateService extends AsyncTask<Void, Void, Void> {
		private ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
		private String Content="";
		@Override
		protected void onPreExecute() {
			dialog.setMessage(getString(R.string.watingLogin));
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
				dato.put(CommonConstants.RequestLoginUser.EMAIL_REQUEST_USER, emailLogin.getText());
				dato.put(CommonConstants.RequestLoginUser.PASSWORD_REQUEST_USER, UtilMethods.encriptValue(passwordLogin.getText().toString()));
				StringEntity entity = new StringEntity(dato.toString());
				post.setEntity(entity);
				
				HttpResponse resp = httpClient.execute(post);
				String respStr = EntityUtils.toString(resp.getEntity());
				System.out.println("La respues que viene : " + respStr);
				Content = respStr;
			} catch (Exception e) {
				dialog.dismiss();
				Toast.makeText(LoginActivity.this, "Hubo un error en el proceso de Registro de Usuario ("+e.getMessage()+"). Disculpe las molestias.",Toast.LENGTH_LONG).show();
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
				System.out.println("codeResponse : "+codeResponse);
				if(CommonConstants.CodeResponse.RESPONSE_SUCCESS_VALIDATION.equals(codeResponse)){
					dbBalletPaper.updateUserDesactive();
//					boolean exitUser=dbBalletPaper.existIdUserService(String.valueOf(idUser));
//					if(exitUser){
//						System.out.println("Tendria que insertarlo no existe en esta Base de Datos");
//						Toast.makeText(LoginActivity.this, "Se va insertar el usuario en SQL LITE "+idUser+"***"+exitUser,Toast.LENGTH_LONG).show();
//						dbBalletPaper.insertUser(email, String.valueOf(idUser));	
//					}
					dbBalletPaper.insertUser(email, String.valueOf(idUser));	
					dbBalletPaper.updateUserActive(String.valueOf(idUser));
					Intent i = new Intent(LoginActivity.this, PrincipalMainActivity.class);
					startActivity(i);
				}else if(CommonConstants.CodeResponse.RESPONSE_FAIL_VALIDATION.equals(codeResponse)){
					Toast.makeText(LoginActivity.this, getString(R.string.validationFail),Toast.LENGTH_LONG).show();
				}else if(CommonConstants.CodeResponse.RESPONSE_EMAIL_NOT_EXIST.equals(codeResponse)){
					Toast.makeText(LoginActivity.this, getString(R.string.emailNotExit),Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(LoginActivity.this, "Hubo un error en la respuesta del Registro de Usuario ("+e.getMessage()+"). Disculpe las molestias.",Toast.LENGTH_LONG).show();
			}
			dialog.dismiss();
		}
		
	}
	
	public void onClickRegisterUser(View v){
		Intent i = new Intent(this, RegisterUserActivity.class);
		startActivity(i);
	}
	
	private boolean validateAllField() {
		System.out.println("Metofo validateAllField");
		boolean validateField = true;
		if (TextUtils.isEmpty(emailLogin.getText())) {
			validateField = false;
			emailLogin.setError(getString(R.string.fieldRequired));
		}
		if(!TextUtils.isEmpty(emailLogin.getText())){
			boolean validateEmail=android.util.Patterns.EMAIL_ADDRESS.matcher(emailLogin.getText()).matches();
			if(!validateEmail){
				validateField = false;
				emailLogin.setError(getString(R.string.emailFormat));
			}
		}		
		if (TextUtils.isEmpty(passwordLogin.getText())) {
			validateField = false;
			passwordLogin.setError(getString(R.string.fieldRequired));
		} 
		return validateField;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
