package com.inmobile.ojovial.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.inmobile.ojovial.R;
import com.inmobile.ojovial.R.id;
import com.inmobile.ojovial.R.layout;
import com.inmobile.ojovial.R.menu;
import com.inmobile.ojovial.R.string;
import com.inmobile.ojovial.bean.ComplaintBean;
import com.inmobile.ojovial.bean.PhotoBean;
import com.inmobile.ojovial.bean.UserSqlLiteBean;
import com.inmobile.ojovial.service.RegisterComplientService;
import com.inmobile.ojovial.service.impl.RegisterComplientServiceImpl;
import com.inmobile.ojovial.sql.DB_BalletPaper;
import com.inmobile.ojovial.util.CommonConstants;
import com.inmobile.ojovial.util.ConvertFormatClass;
import com.inmobile.ojovial.util.GPSTracker;
import com.inmobile.ojovial.util.UtilMethods;
import com.inmobile.ojovial.validation.RegisterComplientValidation;

public class RegisterComplientActivity extends ActionBarActivity implements
		LocationListener {

	private EditText txtNumberPlate;
	private EditText txtComment;
//	private EditText txtFullAddress;
//	private TextView lblLatitude;
//	private TextView lblLongitude;
	private TextView lblGPSAddress,lblShowCoordinates;
	private TextView lblShowHours;
	private DigitalClock dc;
//	private Spinner cboDistrict;
	private LinearLayout linearLayoutForm;
	private LinearLayout linearLayoutProgress;
			
	String urlPhoto1 = "", urlPhoto2 = "", urlPhoto3 = "";
	private DB_BalletPaper dbBalletPaper;
//	List<String> list= new ArrayList<String>();
//	List<String> listAddress= new ArrayList<String>();
	
	boolean completAddres=false,processImageComplete=false;
	
	public ComplaintBean complaintBean=new ComplaintBean();
	public PhotoBean photoBean=new PhotoBean();
	RegisterComplientService registerComplientService=new RegisterComplientServiceImpl();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registercomplient);
		txtComment = (EditText) findViewById(R.id.idTxtAditionalComment);
		txtNumberPlate = (EditText) findViewById(R.id.idTxtPlate);
		lblShowCoordinates = (TextView) findViewById(R.id.idLblCoordinates);
//		lblLatitude = (TextView) findViewById(R.id.idLblLatitude);
//		lblLongitude = (TextView) findViewById(R.id.idLblLongitud);
//		txtFullAddress = (EditText) findViewById(R.id.idTxtFullAddress);
//		cboDistrict = (Spinner) findViewById(R.id.cboDistrict);
		linearLayoutForm=(LinearLayout)findViewById(R.id.lnlyTitleRegisterComplaint);
		linearLayoutProgress=(LinearLayout)findViewById(R.id.lnLyProgress);
		
		lblShowHours = (TextView) findViewById(R.id.idShowHour);
		lblGPSAddress = (TextView) findViewById(R.id.idGPSAddress);
		
		dc = (DigitalClock) findViewById(R.id.digitalClock1);
		
		//--Recover values from image
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			urlPhoto1 = extras.getString("photo1");
			urlPhoto2 = extras.getString("photo2");
			urlPhoto3 = extras.getString("photo3");
		}
		//--Set Phone Bean
		photoBean=ConvertFormatClass.setValuePhotoBean(urlPhoto1,urlPhoto2,urlPhoto3);
		
		//--Get Address
		getPossitionAndAddres(complaintBean);
//		//--Call District
//		registerComplientService.callServiceAllDistrict(RegisterComplientActivity.this, 
//				null, null, complaintBean.getDistrict());
//		
//		list.add("Seleccionar Distrito");
//		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
//   	 	adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//   		cboDistrict.setAdapter(adaptador);
//   		
//   		cboDistrict.setOnItemSelectedListener(new OnItemSelectedListener() {
//			public void onItemSelected(AdapterView<?> parent,android.view.View v, int position, long id) {
//				complaintBean.setDistrict(parent.getItemAtPosition(position).toString());
//				cboDistrict.setSelection(position);
//				if(position>0){
//					complaintBean.setSelectedDistrict(true);
//				}
//			}
//			public void onNothingSelected(AdapterView<?> parent) {
//
//			}
//		});
		
   		//--Process Photo Image
   		new ProcessImage().execute();
   		//--Process Aditional
   		getUserFromDataBaseAndroid();
   		complaintBean.setPhotoBean(photoBean);
	}
	
	private void getUserFromDataBaseAndroid() {
		System.out.println("Entre para sacar datos del android");
		dbBalletPaper = new DB_BalletPaper(RegisterComplientActivity.this, "DB_AndroidBalletPaper", null,1);
		recoverDataForSendService();
	}
	
	private void recoverDataForSendService() {
		UserSqlLiteBean beanUserSQLLite=dbBalletPaper.getRecoverActiveUser();
//		Toast.makeText(RegisterComplientActivity.this,"Ahora si.....Voy a recuperar datos del SQL LITE ==>"+beanUserSQLLite.getIdUserService(),Toast.LENGTH_LONG).show();
		complaintBean.setIdUserService(beanUserSQLLite.getIdUserService());
//		Toast.makeText(RegisterComplientActivity.this,"Terminoooo..!!",Toast.LENGTH_LONG).show();
	}

	public void refreshAddress(View v){
		getPossitionAndAddres(complaintBean);
	}
	
	private void setTouchModeLoginFalse(){
		txtNumberPlate.setFocusable(false);
		txtComment.setFocusable(false);
	}
	
	private void setTouchModeLoginTrue(){
		txtNumberPlate.setFocusableInTouchMode(true);
		txtComment.setFocusableInTouchMode(true);
	}
	
	private void methodError(String messages){
		linearLayoutForm.setVisibility(View.VISIBLE);
		linearLayoutProgress.setVisibility(View.GONE);
		Toast.makeText(RegisterComplientActivity.this,messages,Toast.LENGTH_LONG).show();
		setTouchModeLoginTrue();
	}
	
	public void onClickSaveRegister(View v) {
		// Save information in Service
		boolean validateField= RegisterComplientValidation.isValidateRegisterComplient(RegisterComplientActivity.this,
				txtNumberPlate, txtComment, photoBean, complaintBean);
		if (validateField) {
			//--Set into Complaint Bean
			complaintBean=ConvertFormatClass.setValueComplainBean(txtNumberPlate, txtComment,complaintBean);
			//--Hide keyboard
			UtilMethods.hideKeyboard(this.getCurrentFocus(),RegisterComplientActivity.this);
			//--Call Register
			setTouchModeLoginFalse();
			new SaveInformationDataBaseNew().execute();
		}
	}
	
	private class SaveInformationDataBaseNew extends AsyncTask<Void, Void, Void> {

		private String Content = "";

		@Override
		protected void onPreExecute() {
//			Toast.makeText(gcontext,"DATOS: \n idUser : "+Integer.parseInt(gComplaintBean.getIdUserService())
//					+"\n Longitud : "+gComplaintBean.getLongitude()
//					+"\n Latitude : "+gComplaintBean.getLatitude()
//					+"\n Address : "+UtilMethods.encriptValue(gComplaintBean.getGpsCompleteAddress())+"**"+gComplaintBean.getGpsCompleteAddress()
//					+"\n Comentarios : "+gComplaintBean.getComment()
//					+"\n Numero de Placa : "+gComplaintBean.getNumberPlate()
//					+"\n Categoria de Foto"+CommonConstants.GenericValues.CATEGORY_UPLOAD_IMAGE
//					+"\n Distrito : "+gComplaintBean.getGpsDistrict()
//					+"\n Direccion : "+gComplaintBean.getGpsAddress()
//					+"\n Pais : "+gComplaintBean.getGpsCountry(), Toast.LENGTH_LONG).show();
			linearLayoutProgress.setVisibility(View.VISIBLE);
			System.out.println("Post PRE");
		}

		@SuppressWarnings("deprecation")
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Content=registerComplientService.callServiceRegister(complaintBean);
			} catch (Exception e) {
				methodError(getString(R.string.errorRegistrationComplaint)+e+getString(R.string.sorryMessages));
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			JSONObject jObject = null;

			try {
				jObject = new JSONObject(Content);
				String codeResponse = jObject.getString("codeResponse");
				System.out.println("codeResponse : " + codeResponse);
				int idComplaint = jObject.getInt("idComplient");
				if (CommonConstants.CodeResponse.RESPONSE_SUCCESS_COMPLAINT.equals(codeResponse)) {
					Intent i = new Intent(RegisterComplientActivity.this,SuccessRecordActivity.class);
					i.putExtra(CommonConstants.GenericValues.IDCOMPLIENT,String.valueOf(idComplaint));
					startActivity(i);
				} else {
					methodError(getString(R.string.errorSaveComplaint));
				}
			} catch (Exception e) {
				methodError(getString(R.string.errorRegistrationComplaint)+e+getString(R.string.sorryMessages));
			}
		}

	}

	private class ProcessImage extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {

		}
		@Override
		protected Void doInBackground(Void... params) {
			try {
				registerComplientService.processImage();
			} catch (Exception e) {
				Toast.makeText(RegisterComplientActivity.this,"Error convirtiendo archivos a hexadecimal : "+ e.getMessage(), Toast.LENGTH_LONG).show();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			photoBean.setCompleteProcessImage(true);
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

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onBackPressed(){
		Toast.makeText(getApplicationContext(),getString(R.string.notBackStep),Toast.LENGTH_LONG).show();
	}
	
	@SuppressLint("NewApi")
	public void getPossitionAndAddres(ComplaintBean complaintBean) {
		// create class object
		GPSTracker gps = new GPSTracker(RegisterComplientActivity.this);

		// check if GPS enabled
		if (gps.canGetLocation()) {

			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();

			complaintBean.setLatitude(String.valueOf(latitude));
			complaintBean.setLongitude(String.valueOf(longitude));
			lblShowCoordinates.setText(this.getString(R.string.strLblShowGPS)+String.valueOf(latitude)+" ; "+String.valueOf(longitude)+")");
			Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());

			if (Geocoder.isPresent()) {
				try {
					List<Address> addresses = geo.getFromLocation(latitude,	longitude, 1);
					for(Address beanAddress:addresses){
						complaintBean.setGpsCompleteAddress(beanAddress.getAddressLine(0)+", "+ beanAddress.getLocality()+", "+beanAddress.getSubAdminArea());
						complaintBean.setGpsAddress(beanAddress.getAddressLine(0));
						complaintBean.setGpsDistrict(beanAddress.getLocality());
						complaintBean.setGpsCountry(beanAddress.getCountryName());
						completAddres=true;
					}
				} catch (IOException e) {
					Toast.makeText(getApplicationContext(),"Ocurrio un error : " + e.getMessage(),Toast.LENGTH_LONG).show();
				}
			}else{
				completAddres=false;
				complaintBean.setGpsCompleteAddress("Sin Dirección");
			}
			
		} else {
			gps.showSettingsAlert();
		}
		lblGPSAddress.setText(complaintBean.getGpsCompleteAddress());
	}
	
	
	
//	public void executeTimer(){
//		System.out.println("executeTimer");
//		Timer myTimer=new Timer();
//		myTimer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				UtilMethods.alertbox(getString(R.string.titleAdvertencia),
//						getString(R.string.messagesOptionOnlyForWeb),
//						RegisterComplientActivity.this, R.drawable.advertencia);
//				Intent i = new Intent(RegisterComplientActivity.this, PrincipalMainActivity.class);
//				startActivity(i);
//			}
//		}, 420000);
//	}
//	
//	@Override
//	public void onUserInteraction(){
//		executeTimer();
//	}
}