package com.example.balletpaper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.balletpaper.util.CommonConstants;
import com.example.balletpaper.util.UtilMethods;

public class RegisterUserActivity extends ActionBarActivity {

	private EditText registerEmail;
	private EditText registerpassword;
	private EditText registerName;
	private EditText registerLastName;
	private EditText registerDni;
	private CheckBox registerAdult;
	private CheckBox registerAceptTermin;
	private Button registerSaveBtn;
	private String responseService="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registeruser);
		System.out.println("Dentro de crear");
		registerEmail = (EditText) findViewById(R.id.idRegisterEmail);
		registerpassword = (EditText) findViewById(R.id.idRegisterPassword);
		registerName = (EditText) findViewById(R.id.idNameUser);
		registerLastName = (EditText) findViewById(R.id.idLastNameUser);
		registerDni = (EditText) findViewById(R.id.idDniUser);
		registerAdult = (CheckBox) findViewById(R.id.idChkAdult);
		registerAceptTermin = (CheckBox) findViewById(R.id.idChkAcceptTerm);

		registerSaveBtn = (Button) findViewById(R.id.idBtnRegister);

	}

	public void onClickSaveUser(View v) {
		boolean validateField = validateAllField();
		if (validateField) {
			new SaveInformationService().execute();
		}

	}

	private class SaveInformationService extends AsyncTask<Void, Void, Void> {
		private ProgressDialog dialog = new ProgressDialog(RegisterUserActivity.this);
		private String Content="";

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Por favor, espere estamo registrando su cuenta");
			dialog.show();
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
				dato.put(CommonConstants.RequestValueUser.EMAIL_REQUEST_USER, registerEmail.getText());
				dato.put(CommonConstants.RequestValueUser.PASSWORD_REQUEST_USER, UtilMethods.encryptIt(registerpassword.getText().toString()));
				dato.put(CommonConstants.RequestValueUser.TYPECUSTOMER_REQUEST_USER, "1");
				dato.put(CommonConstants.RequestValueUser.NAMEUSER_REQUEST_USER, registerName.getText());
				dato.put(CommonConstants.RequestValueUser.LASTNAMEUSER_REQUEST_USER, registerLastName.getText());
				dato.put(CommonConstants.RequestValueUser.DNIUSER_REQUEST_USER, registerDni.getText());
				dato.put(CommonConstants.RequestValueUser.RECORDINGDEVICE_REQUEST_USER, CommonConstants.GenericValues.MOBILE_RECORD);
				
				StringEntity entity = new StringEntity(dato.toString());
				post.setEntity(entity);
				
				HttpResponse resp = httpClient.execute(post);
				String respStr = EntityUtils.toString(resp.getEntity());
				System.out.println("La respues que viene : " + respStr);
				Content = respStr;
			} catch (Exception e) {
				// Close progress dialog
				dialog.dismiss();
				Toast.makeText(RegisterUserActivity.this, "Hubo un error en el proceso de Registro de Usuario ("+e.getMessage()+"). Disculpe las molestias.",Toast.LENGTH_LONG).show();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// Close progress dialog
			dialog.dismiss();
			
			JSONObject jObject = null;
			try {
				jObject = new JSONObject(Content);
				String codeResponse = jObject.getString("codeResponse");
				System.out.println("codeResponse : "+codeResponse);
				if(CommonConstants.CodeResponse.RESPONSE_SUCCESS_USER.equals(codeResponse)){
					Intent i = new Intent(RegisterUserActivity.this, WelcomeRegisterActivity.class);
					startActivity(i);
				}else if(CommonConstants.CodeResponse.RESPONSE_EMAIL_EXIST.equals(codeResponse)){
					Toast.makeText(RegisterUserActivity.this, getString(R.string.emailExit),Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(RegisterUserActivity.this, "Hubo un error en la respuesta del Registro de Usuario ("+e.getMessage()+"). Disculpe las molestias.",Toast.LENGTH_LONG).show();
			}
		}

	}

	private boolean validateAllField() {
		System.out.println("Metofo validateAllField");
		boolean validateField = true;
		if (TextUtils.isEmpty(registerEmail.getText())) {
			validateField = false;
			registerEmail.setError(getString(R.string.fieldRequired));
		} else if (TextUtils.isEmpty(registerpassword.getText())) {
			validateField = false;
			registerpassword.setError(getString(R.string.fieldRequired));
		} else if (TextUtils.isEmpty(registerName.getText())) {
			validateField = false;
			registerName.setError(getString(R.string.fieldRequired));
		} else if (TextUtils.isEmpty(registerLastName.getText())) {
			validateField = false;
			registerLastName.setError(getString(R.string.fieldRequired));
		} else if (TextUtils.isEmpty(registerDni.getText())) {
			validateField = false;
			registerDni.setError(getString(R.string.fieldRequired));
		} else if (!registerAdult.isChecked()) {
			validateField = false;
			registerAdult.setError(getString(R.string.fieldRequired));
		} else if (!registerAceptTermin.isChecked()) {
			validateField = false;
			registerAceptTermin.setError(getString(R.string.fieldRequired));
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
