package com.inmobile.ojovial.activity;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.inmobile.ojovial.R;
import com.inmobile.ojovial.bean.LoginBean;
import com.inmobile.ojovial.service.LoginService;
import com.inmobile.ojovial.service.impl.LoginServiceImpl;
import com.inmobile.ojovial.sql.DB_BalletPaper;
import com.inmobile.ojovial.util.CommonConstants;
import com.inmobile.ojovial.util.ConvertFormatClass;
import com.inmobile.ojovial.util.UtilMethods;
import com.inmobile.ojovial.validation.LoginValidation;

public class LoginActivity extends ActionBarActivity{

	private EditText emailLogin;
	private EditText passwordLogin;
//	private TextView txtLnkRegisterUser;
	private DB_BalletPaper dbBalletPaper;
	private LinearLayout linearLayoutForm;
	private LinearLayout linearLayoutProgress;
	private Button btnLogin;
	private Button btnRegister;
	LoginService loginService=new LoginServiceImpl();
	LoginBean loginBean=new LoginBean();
	boolean resultServices=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_login);
		linearLayoutForm=(LinearLayout)findViewById(R.id.lnLyFormLogin);
		linearLayoutProgress=(LinearLayout)findViewById(R.id.lnLyProgress);
		emailLogin=(EditText)findViewById(R.id.idLoginEmail);
		passwordLogin=(EditText)findViewById(R.id.idLoginPassword);
//		txtLnkRegisterUser=(TextView)findViewById(R.id.link_to_register);
		btnLogin=(Button)findViewById(R.id.ok_button);
		btnRegister=(Button)findViewById(R.id.link_to_register);
//		dataTest();
		createAndroidDatase();
		if(dbBalletPaper.existUserLoginToApplication()){
			Intent i = new Intent(LoginActivity.this, PrincipalMainActivity.class);
			startActivity(i);
		}
	}
	
	private void dataTest(){
		emailLogin.setText("alberto_j10@hotmail.com");
		passwordLogin.setText("12345678");
	}
	
	private void createAndroidDatase() {
		dbBalletPaper = new DB_BalletPaper(this, "DB_AndroidBalletPaper", null, 1);
	}
	
	public void onOkClick(View v){
		
		boolean validateField=LoginValidation.isValidateLoginFields(LoginActivity.this,emailLogin,passwordLogin);
		loginBean=ConvertFormatClass.setValuesLoginBean(emailLogin,passwordLogin);
		if(validateField){
			UtilMethods.hideKeyboard(this.getCurrentFocus(),LoginActivity.this);
			new LoginValidateService().execute();	
			setTouchModeLoginFalse();
		}
	}

	public void onClickRegisterUser(View v){
		Intent i = new Intent(this, RegisterUserActivity.class);
		startActivity(i);
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
	
	private void setTouchModeLoginFalse(){
		emailLogin.setFocusable(false);
		passwordLogin.setFocusable(false);
		btnLogin.setEnabled(false);
		btnRegister.setEnabled(false);
		
	}
	
	private void setTouchModeLoginTrue(){
		emailLogin.setFocusableInTouchMode(true);
		passwordLogin.setFocusableInTouchMode(true);
		btnLogin.setEnabled(true);
		btnRegister.setEnabled(true);
	}
	
	private void methodError(String messages){
		linearLayoutForm.setVisibility(View.VISIBLE);
		linearLayoutProgress.setVisibility(View.GONE);	
		Toast.makeText(LoginActivity.this, messages,Toast.LENGTH_LONG).show();
		setTouchModeLoginTrue();
	}
	
	private class LoginValidateService extends AsyncTask<Void, Void, Void> {
		private String Content="";
		@Override
		protected void onPreExecute() {
			resultServices=true;
			linearLayoutProgress.setVisibility(View.VISIBLE);
		}
		@SuppressWarnings("deprecation")
		@Override
		protected Void doInBackground(Void... params) {
			try {
				
				Content=loginService.callService(loginBean);
			} catch (Exception e) {
				resultServices=false;
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			if(!resultServices){
				methodError(getString(R.string.connectionRefused));
			}else{
				JSONObject jObject = null;
				try {
					jObject = new JSONObject(Content);
					String codeResponse = jObject.getString("codeResponse");
					String email = jObject.getString("additional");
					int idUser = jObject.getInt("idUser");
					System.out.println("codeResponse : "+codeResponse+"************++");
					if(CommonConstants.CodeResponse.RESPONSE_SUCCESS_VALIDATION.equals(codeResponse)){
						try {
							loginService.sucessLogin(String.valueOf(idUser), email,dbBalletPaper);	
							Intent i = new Intent(LoginActivity.this, PrincipalMainActivity.class);
							startActivity(i);
						} catch (Exception e) {
							methodError(getString(R.string.errorInGeneral));
						}
					}else if(CommonConstants.CodeResponse.RESPONSE_FAIL_VALIDATION.equals(codeResponse)){
						methodError(getString(R.string.validationFail));
					}else if(CommonConstants.CodeResponse.RESPONSE_EMAIL_NOT_EXIST.equals(codeResponse)){
						methodError(getString(R.string.emailNotExit));
					}
				} catch (Exception e) {
					methodError(getString(R.string.errorUser)+e+getString(R.string.sorryMessages));
				}
			}
			linearLayoutForm.setVisibility(View.VISIBLE);
			linearLayoutProgress.setVisibility(View.GONE);	
		}
	}
	
	@Override
	public void onBackPressed(){
		Toast.makeText(getApplicationContext(),getString(R.string.notBackStepGeneral),Toast.LENGTH_LONG).show();
	}
}

