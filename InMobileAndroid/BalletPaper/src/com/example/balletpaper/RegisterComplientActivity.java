package com.example.balletpaper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.balletpaper.bean.ComplaintBean;
import com.example.balletpaper.bean.PhotoBean;
import com.example.balletpaper.service.RegisterComplientService;
import com.example.balletpaper.service.impl.RegisterComplientServiceImpl;
import com.example.balletpaper.sql.DB_BalletPaper;
import com.example.balletpaper.util.ConvertFormatClass;
import com.example.balletpaper.util.GPSTracker;
import com.example.balletpaper.validation.RegisterComplientValidation;

public class RegisterComplientActivity extends ActionBarActivity implements
		LocationListener {

	private EditText txtNumberPlate;
	private EditText txtComment;
	private EditText txtFullAddress;
	private TextView lblLatitude;
	private TextView lblLongitude;
	private Spinner cboDistrict,cboSpecificAddress;
	private LinearLayout linearLayoutForm;
	private LinearLayout linearLayoutProgress;
	private ProgressBar processBarPhoto;
	private LinearLayout linearLayoutPhoto;
			
	String urlPhoto1 = "", urlPhoto2 = "", urlPhoto3 = "";
	private DB_BalletPaper dbBalletPaper;
	List<String> list= new ArrayList<String>();
	List<String> listAddress= new ArrayList<String>();
	
	boolean completAddres=false,processImageComplete=false;
	
	public ComplaintBean complaintBean=new ComplaintBean();
	public PhotoBean photoBean=new PhotoBean();
	RegisterComplientService registerComplientService=new RegisterComplientServiceImpl();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registercomplient);
		txtComment = (EditText) findViewById(R.id.idTxtAditionalComment);
		txtNumberPlate = (EditText) findViewById(R.id.idTxtPlate);
		lblLatitude = (TextView) findViewById(R.id.idLblLatitude);
		lblLongitude = (TextView) findViewById(R.id.idLblLongitud);
		txtFullAddress = (EditText) findViewById(R.id.idTxtFullAddress);
		cboDistrict = (Spinner) findViewById(R.id.cboDistrict);
		cboSpecificAddress=(Spinner)findViewById(R.id.cboSpecificDistrict);
		linearLayoutForm=(LinearLayout)findViewById(R.id.lnlyRegisterComplaint);
		linearLayoutProgress=(LinearLayout)findViewById(R.id.lnLyProgress);
		
		linearLayoutPhoto=(LinearLayout)findViewById(R.id.lnLyProgressPhoto);
		processBarPhoto=(ProgressBar)findViewById(R.id.progressPhoto);
		
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
		
		//--Set Information into Dinamic Combo
		ArrayAdapter<String> addressArray = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listAddress);
		addressArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
   		cboSpecificAddress.setAdapter(addressArray);
   		final int totalAddres=addressArray.getCount();
		cboSpecificAddress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent,android.view.View v, int position, long id) {
				if(!completAddres&&position==0){
					txtFullAddress.setVisibility(View.VISIBLE);
					cboDistrict.setVisibility(View.VISIBLE);
				}
				if(totalAddres-1==position&&completAddres){
					txtFullAddress.setVisibility(View.VISIBLE);
					cboDistrict.setVisibility(View.VISIBLE);
				}else if(totalAddres-2==position){
					//--Update Address
					listAddress.clear();
					getPossitionAndAddres(complaintBean);
					cboSpecificAddress.setSelection(0);
				}else{				
					txtFullAddress.setVisibility(View.GONE);
					cboDistrict.setVisibility(View.GONE);
					if(completAddres){
						String[] arrayValueAddress=parent.getItemAtPosition(position).toString().split(",");
						String valueSpecifiAddres=arrayValueAddress[1];
						complaintBean.setDistrict(valueSpecifiAddres);
						complaintBean.setAlternativeAddress(parent.getItemAtPosition(position).toString());
						complaintBean.setSelectedDistrict(false);
					}
					
				}
			}
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
   		
		//--Call District
		registerComplientService.callServiceAllDistrict(RegisterComplientActivity.this, 
				cboDistrict, list, complaintBean.getDistrict());
		
		list.add("Seleccionar Distrito");
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
   	 	adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
   		cboDistrict.setAdapter(adaptador);
   		
   		cboDistrict.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent,android.view.View v, int position, long id) {
				complaintBean.setDistrict(parent.getItemAtPosition(position).toString());
				cboDistrict.setSelection(position);
				if(position>0){
					complaintBean.setSelectedDistrict(true);
				}
			}
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
   		//--Process Photo Image
   		registerComplientService.proccesImage(RegisterComplientActivity.this, photoBean,linearLayoutPhoto,processBarPhoto);
   		//--Process Aditional
   		registerComplientService.processAditional(RegisterComplientActivity.this, complaintBean, dbBalletPaper);
   		
   		complaintBean.setPhotoBean(photoBean);
	}

	public void onClickSaveRegister(View v) {
		// Save information in Service
		boolean validateField= RegisterComplientValidation.isValidateRegisterComplient(RegisterComplientActivity.this,
				txtNumberPlate, txtComment, txtFullAddress, cboDistrict, cboSpecificAddress, photoBean, complaintBean);
		//--Set into Complaint Bean
		complaintBean=ConvertFormatClass.setValueComplainBean(txtNumberPlate, txtComment, txtFullAddress,complaintBean);
		if (validateField) {
			registerComplientService.callServiceRegisterComplaint(RegisterComplientActivity.this, complaintBean,
					linearLayoutForm,linearLayoutProgress);
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

	@SuppressLint("NewApi")
	public void getPossitionAndAddres(ComplaintBean complaintBean) {
		// create class object
		GPSTracker gps = new GPSTracker(RegisterComplientActivity.this);

		// check if GPS enabled
		if (gps.canGetLocation()) {

			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();

			lblLatitude.setText(latitude + "");
			lblLongitude.setText(longitude + "");
			complaintBean.setLatitude(String.valueOf(latitude));
			complaintBean.setLongitude(String.valueOf(longitude));
			Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());

			if (Geocoder.isPresent()) {
				try {
					List<Address> addresses = geo.getFromLocation(latitude,	longitude, 1);
					for(Address beanAddress:addresses){
						complaintBean.setDistrict(beanAddress.getLocality().toUpperCase());
						listAddress.add(beanAddress.getAddressLine(0)+", "+ beanAddress.getLocality()+", "+beanAddress.getCountryName());
						complaintBean.setAlternativeAddress(beanAddress.getAddressLine(0));
						completAddres=true;
					}
					
				} catch (IOException e) {
					Toast.makeText(getApplicationContext(),"Ocurrio un error : " + e.getMessage(),Toast.LENGTH_LONG).show();
				}
			}
			
		} else {
			gps.showSettingsAlert();
		}
		listAddress.add("Actualizar Ubicación");
		listAddress.add("Agregar otra dirección");
	}
	
	
}
