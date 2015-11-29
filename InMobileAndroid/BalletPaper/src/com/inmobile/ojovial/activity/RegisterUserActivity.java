package com.inmobile.ojovial.activity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.inmobile.ojovial.R;
import com.inmobile.ojovial.R.id;
import com.inmobile.ojovial.R.layout;
import com.inmobile.ojovial.R.menu;
import com.inmobile.ojovial.bean.RegisterUserBean;
import com.inmobile.ojovial.service.RegisterUserService;
import com.inmobile.ojovial.service.impl.RegisterUserServiceImpl;
import com.inmobile.ojovial.sql.DB_BalletPaper;
import com.inmobile.ojovial.util.CommonConstants;
import com.inmobile.ojovial.util.ConvertFormatClass;
import com.inmobile.ojovial.util.UtilMethods;
import com.inmobile.ojovial.validation.RegisterUserValidation;

public class RegisterUserActivity extends ActionBarActivity {

	private EditText registerEmail;
	private EditText registerpassword;
	private EditText registerName;
	private EditText registerLastName;
	private EditText registerDni;
	private EditText registerpasswordConfirm;
	private CheckBox registerAdult;
	private CheckBox registerAceptTermin;
	private DB_BalletPaper dbBalletPaper;
	private LinearLayout linearLayoutForm;
	private LinearLayout linearLayoutProgress;
	private Button btnRegisterUser;
	RegisterUserService registerUserService=new RegisterUserServiceImpl();
	RegisterUserBean registerUserBean=new RegisterUserBean();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_register_user);
		System.out.println("Dentro de crear");
		registerEmail = (EditText) findViewById(R.id.idRegisterEmail);
		registerpassword = (EditText) findViewById(R.id.idRegisterPassword);
		registerpasswordConfirm = (EditText) findViewById(R.id.idRegisterPasswordConfirm);
		registerName = (EditText) findViewById(R.id.idNameUser);
		registerLastName = (EditText) findViewById(R.id.idLastNameUser);
		registerDni = (EditText) findViewById(R.id.idDniUser);
		registerAdult = (CheckBox) findViewById(R.id.idChkAdult);
		registerAceptTermin = (CheckBox) findViewById(R.id.idChkAcceptTerm);
		linearLayoutForm=(LinearLayout)findViewById(R.id.lnlyRegisterUser);
		linearLayoutProgress=(LinearLayout)findViewById(R.id.lnLyProgress);
		btnRegisterUser=(Button)findViewById(R.id.idBtnRegister);
//		putInformationTest();
		createAndroidDatase();
		

	}
	
	private void putInformationTest(){
		registerEmail.setText("testemail@gmail.com");
		registerpassword.setText("12345");
		registerpasswordConfirm.setText("12345");
		registerName.setText("Test Prueba");
		registerLastName.setText("Last Name");
		registerDni.setText("45700739");
		registerAceptTermin.setChecked(true);
		registerAdult.setChecked(true);
	}

	private void createAndroidDatase() {
		dbBalletPaper = new DB_BalletPaper(this, "DB_AndroidBalletPaper", null, 1);
	}

	public void onClickSaveUser(View v) {
		boolean validateField = RegisterUserValidation.isValidateRegisterUserFields(RegisterUserActivity.this,registerEmail,
				registerpassword,registerpasswordConfirm,registerName,registerLastName,registerDni,registerAdult,registerAceptTermin);
		if (validateField) {
			registerUserBean=ConvertFormatClass.setValuesRegisterUserBean(registerEmail, registerpassword, registerName,
					registerLastName, registerDni, "1", CommonConstants.GenericValues.MOBILE_RECORD);
			UtilMethods.hideKeyboard(this.getCurrentFocus(),RegisterUserActivity.this);
			setTouchModeLoginFalse();
			new SaveInformationService().execute();
		}

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
		registerName.setFocusable(false);
		registerLastName.setFocusable(false);
		registerEmail.setFocusable(false);
		registerpassword.setFocusable(false);
		registerpasswordConfirm.setFocusable(false);
		registerDni.setFocusable(false);
		registerAceptTermin.setFocusable(false);
		registerAdult.setFocusable(false);
		btnRegisterUser.setEnabled(false);
	}

	private void setTouchModeLoginTrue(){
		registerName.setFocusableInTouchMode(true);
		registerLastName.setFocusableInTouchMode(true);
		registerEmail.setFocusableInTouchMode(true);
		registerpassword.setFocusableInTouchMode(true);
		registerpasswordConfirm.setFocusableInTouchMode(true);
		registerDni.setFocusableInTouchMode(true);
		registerAceptTermin.setFocusableInTouchMode(true);
		registerAdult.setFocusableInTouchMode(true);
		btnRegisterUser.setEnabled(true);
	}
	
	private void methodError(String messages){
		linearLayoutForm.setVisibility(View.VISIBLE);
		linearLayoutProgress.setVisibility(View.GONE);	
		Toast.makeText(RegisterUserActivity.this, messages,Toast.LENGTH_LONG).show();
		setTouchModeLoginTrue();
	}
	
	private class SaveInformationService extends AsyncTask<Void, Void, Void> {
		private String Content="";

		@Override
		protected void onPreExecute() {
//			gLinearLayoutForm.setVisibility(View.GONE);
			linearLayoutProgress.setVisibility(View.VISIBLE);
		}

		@SuppressWarnings("deprecation")
		@Override
		protected Void doInBackground(Void... params) {
			System.out.println("onLoad!!!");
			try {
				Content=registerUserService.callRegisterUserService(registerUserBean);	
//				Content="{'idUser':120,'codeResponse':'SUCCESS_INSERT_USER','messagesResponse':'The user was created successfully.','typeUser':0}";
			} catch (Exception e) {
				methodError(getString(R.string.errorUser)+e+getString(R.string.sorryMessages));
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
					registerUserService.sucessUserRegister(registerUserBean.getRegisterEmail(), String.valueOf(idUserService),dbBalletPaper);
					Intent i = new Intent(RegisterUserActivity.this, WelcomeRegisterActivity.class);
					startActivity(i);
				}else if(CommonConstants.CodeResponse.RESPONSE_EMAIL_EXIST.equals(codeResponse)){
					methodError(getString(R.string.emailExit));
				}
			} catch (Exception e) {
				methodError(getString(R.string.errorUser)+e+getString(R.string.sorryMessages));
			}
//			linearLayoutForm.setVisibility(View.VISIBLE);
			linearLayoutProgress.setVisibility(View.GONE);	
		}

	}

}
