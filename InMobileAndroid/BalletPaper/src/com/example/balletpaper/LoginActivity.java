package com.example.balletpaper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.balletpaper.bean.LoginBean;
import com.example.balletpaper.service.LoginService;
import com.example.balletpaper.service.impl.LoginServiceImpl;
import com.example.balletpaper.sql.DB_BalletPaper;
import com.example.balletpaper.util.ConvertFormatClass;
import com.example.balletpaper.util.UtilMethods;
import com.example.balletpaper.validation.LoginValidation;

public class LoginActivity extends ActionBarActivity{

	private EditText emailLogin;
	private EditText passwordLogin;
	private TextView txtLnkRegisterUser;
	private DB_BalletPaper dbBalletPaper;
	private LinearLayout linearLayoutForm;
	private LinearLayout linearLayoutProgress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginnew2);
		linearLayoutForm=(LinearLayout)findViewById(R.id.lnLyFormLogin);
		linearLayoutProgress=(LinearLayout)findViewById(R.id.lnLyProgress);
		emailLogin=(EditText)findViewById(R.id.idLoginEmail);
		passwordLogin=(EditText)findViewById(R.id.idLoginPassword);
		txtLnkRegisterUser=(TextView)findViewById(R.id.link_to_register);
		createAndroidDatase();
	}
	
	private void createAndroidDatase() {
		dbBalletPaper = new DB_BalletPaper(this, "DB_AndroidBalletPaper", null, 1);
	}
	
	public void onOkClick(View v){
		boolean validateField=LoginValidation.isValidateLoginFields(LoginActivity.this,emailLogin,passwordLogin);
		LoginBean loginBean=ConvertFormatClass.setValuesLoginBean(emailLogin,passwordLogin);
		if(validateField){
			UtilMethods.hideKeyboard(this.getCurrentFocus(),LoginActivity.this);
//			setFocusableLogin();
			LoginService loginService=new LoginServiceImpl();
			loginService.callServiceLogin(LoginActivity.this, dbBalletPaper,loginBean,linearLayoutForm,linearLayoutProgress);
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
	
	private void setFocusableLogin(){
		emailLogin.setFocusableInTouchMode(false);
		passwordLogin.setFocusableInTouchMode(false);
	}
	
	
}
