package com.example.balletpaper;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.balletpaper.bean.RegisterUserBean;
import com.example.balletpaper.service.RegisterUserService;
import com.example.balletpaper.service.impl.RegisterUserServiceImpl;
import com.example.balletpaper.sql.DB_BalletPaper;
import com.example.balletpaper.util.CommonConstants;
import com.example.balletpaper.util.ConvertFormatClass;
import com.example.balletpaper.util.UtilMethods;
import com.example.balletpaper.validation.RegisterUserValidation;

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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registeruser);
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
		createAndroidDatase();

	}

	private void createAndroidDatase() {
		dbBalletPaper = new DB_BalletPaper(this, "DB_AndroidBalletPaper", null, 1);
	}

	public void onClickSaveUser(View v) {
		boolean validateField = RegisterUserValidation.isValidateRegisterUserFields(RegisterUserActivity.this,registerEmail,
				registerpassword,registerpasswordConfirm,registerName,registerLastName,registerDni,registerAdult,registerAceptTermin);
		RegisterUserBean registerUserBean=ConvertFormatClass.setValuesRegisterUserBean(registerEmail, registerpassword, registerName,
				registerLastName, registerDni, "1", CommonConstants.GenericValues.MOBILE_RECORD);
		if (validateField) {
			UtilMethods.hideKeyboard(this.getCurrentFocus(),RegisterUserActivity.this);
			RegisterUserService registerUserService=new RegisterUserServiceImpl();
			registerUserService.callServiceRegisterUser(RegisterUserActivity.this, registerUserBean,
					dbBalletPaper,linearLayoutForm,linearLayoutProgress);
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
}
