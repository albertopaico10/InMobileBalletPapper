package com.example.balletpaper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import com.example.balletpaper.sql.DB_BalletPaper;
import com.example.balletpaper.util.CommonConstants;
import com.example.balletpaper.util.GPSTracker;
import com.example.balletpaper.util.UtilMethods;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.support.v7.app.ActionBarActivity;
import android.test.UiThreadTest;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterComplientActivity extends ActionBarActivity implements
		LocationListener {

	private EditText txtNumberPlate;
	private EditText txtComment;
	private TextView lblLatitude;
	private TextView lblLongitude;
	private TextView lblDistrict;
	private EditText txtFullAddress;
	private Spinner cboDistrict,cboSpecificAddress;
	GPSTracker gps;
	String responseComplient, responseImageService1 = "",
			responseImageService2 = "", responseImageService3 = "";
	int idComplaintService = 0;
	String urlPhoto1 = "", urlPhoto2 = "", urlPhoto3 = "";
	String hexPhoto1 = "", hexPhoto2 = "", hexPhoto3 = "";
	String finalLatitude = "", finalLongitude = "", finalDistrict = "";
	private String finalAddress = "", idUserService = "";
	private DB_BalletPaper dbBalletPaper;
	String[] districtArray=null;
	List<String> list= new ArrayList<String>();
	List<String> listAddress= new ArrayList<String>();
	ProgressDialog dialogGeneral=null;
	int totalAddres=0;
	boolean completAddres=false,selectedDistrict=false,processImageComplete=false;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		dialogGeneral = new ProgressDialog(RegisterComplientActivity.this);
		dialogGeneral.setMessage("Cargando la información necesaria...");
		dialogGeneral.show();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registercomplient);
		txtComment = (EditText) findViewById(R.id.idTxtAditionalComment);
		txtNumberPlate = (EditText) findViewById(R.id.idTxtPlate);
		lblLatitude = (TextView) findViewById(R.id.idLblLatitude);
		lblLongitude = (TextView) findViewById(R.id.idLblLongitud);
		txtFullAddress = (EditText) findViewById(R.id.idTxtFullAddress);
		lblDistrict = (TextView) findViewById(R.id.idLblDistrict);
		cboDistrict = (Spinner) findViewById(R.id.cboDistrict);
		cboSpecificAddress=(Spinner)findViewById(R.id.cboSpecificDistrict);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			urlPhoto1 = extras.getString("photo1");
			urlPhoto2 = extras.getString("photo2");
			urlPhoto3 = extras.getString("photo3");
		}
		
		getPossitionAndAddres();
//		new GetAddresThreat().execute();
		
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
				}else{
					
					txtFullAddress.setVisibility(View.GONE);
					cboDistrict.setVisibility(View.GONE);
					if(completAddres){
						String[] arrayValueAddress=parent.getItemAtPosition(position).toString().split(",");
						String valueSpecifiAddres=arrayValueAddress[1];
						finalDistrict=valueSpecifiAddres;
						finalAddress=parent.getItemAtPosition(position).toString();	
					}
					
				}
			}
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
   		
		
		new getListDistrict().execute();
		
		list.add("SELECCIONAR DISTRITO");
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
   	 	adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
   		cboDistrict.setAdapter(adaptador);
   		
   		cboDistrict.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent,android.view.View v, int position, long id) {
				finalDistrict=parent.getItemAtPosition(position).toString();
				cboDistrict.setSelection(position);
				if(position>0){
					selectedDistrict=true;
				}
			}
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
   		
   		new ProcessImage().execute();
   		
   		new ProccesAdditionalInformation().execute();
		

//		txtFullAddress.setText(finalAddress);
	}
	
	private class ProccesAdditionalInformation extends AsyncTask<Void, Void, Void> {
		private ProgressDialog dialog = new ProgressDialog(RegisterComplientActivity.this);

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Procesando información adicional \n Gracias.");
			dialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			getUserFromDataBaseAndroid();
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			dialog.dismiss();
		}
	}
	
	private class ProcessImage extends AsyncTask<Void, Void, Void> {
		private ProgressDialog dialog = new ProgressDialog(RegisterComplientActivity.this);

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Estamos procesando las images para adjuntarlas a la denuncia. Por favor Espere. \n Gracias");
			dialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			try {
				processImage();
			} catch (Exception e) {
				Toast.makeText(RegisterComplientActivity.this,"Error convirtiendo archivos a hexadecimal : "+ e.getMessage(), Toast.LENGTH_LONG).show();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			processImageComplete=true;
			Toast.makeText(RegisterComplientActivity.this,"Se completo el proceso de tratamiento de las fotos. \n Gracias", Toast.LENGTH_LONG).show();
			dialog.dismiss();
		}
		
	}
	
	private class GetAddresThreat extends AsyncTask<Void, Void, Void> {
		private ProgressDialog dialog = new ProgressDialog(RegisterComplientActivity.this);

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Estamos ubicando, su posicion. Por favor Espere. \n Dependemos de su conexion a Internet");
			dialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			getPossitionAndAddres();
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			dialog.dismiss();
		}
		
	}

	private class getListDistrict extends AsyncTask<Void, Void, Void> {
		private ProgressDialog dialog = new ProgressDialog(RegisterComplientActivity.this);
		private String Content = "";

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Por favor, cargando los distritos");
			dialog.show();
		}

		@SuppressWarnings("deprecation")
		@Override
		protected Void doInBackground(Void... params) {
			System.out.println("onLoad!!!");
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet get = new HttpGet(CommonConstants.URLService.GET_DISTRICT);
			get.setHeader("content-type", "application/json; charset=UTF-8");
			// Construimos el objeto cliente en formato JSON
			String respStr = "";
			try {
				HttpResponse resp = httpClient.execute(get);
				respStr = EntityUtils.toString(resp.getEntity());
				System.out.println("La respues que viene : " + respStr);
				Content = respStr;
			} catch (Exception e) {
				System.out.println("Error : " + e.getMessage());
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// Close progress dialog
			dialog.dismiss();
			
            JSONObject jsonResponse;
            
            try {
            	 jsonResponse = new JSONObject(Content);
            	 System.out.println("1");
            	 JSONArray jsonMainNode = jsonResponse.optJSONArray("ubigeoBean");
            	 System.out.println("2");
            	 int lengthJsonArr = jsonMainNode.length();  
            	 districtArray=new String[lengthJsonArr];
            	 System.out.println("3---"+lengthJsonArr);
            	 for(int i=0; i < lengthJsonArr; i++) {
            		 JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
            		 String id       = jsonChildNode.optString("id").toString();
                     String name     = jsonChildNode.optString("name").toString();
                     list.add(name);
                 }
            	 Toast.makeText(RegisterComplientActivity.this, "District "+finalDistrict, Toast.LENGTH_LONG);
            	 int position=list.indexOf(finalDistrict);
            	 Toast.makeText(RegisterComplientActivity.this, "Posicion "+position, Toast.LENGTH_LONG);
            	 if(position==-1){
            		 cboDistrict.setSelection(0);	
            	 }else{
            		 cboDistrict.setSelection(position);	 
            	 }
            	 
			} catch (Exception e) {
				Toast.makeText(RegisterComplientActivity.this,"Hubo un error en la respuesta de los ditritos("+ e.getMessage() + "). Disculpe las molestias.",Toast.LENGTH_LONG).show();
			}
            System.out.println("CHAUUuuu");
            dialogGeneral.dismiss();
		}


	}

	private void getUserFromDataBaseAndroid() {
		dbBalletPaper = new DB_BalletPaper(this, "DB_AndroidBalletPaper", null,1);
		idUserService = dbBalletPaper.getIdUserService();

	}

	public void onClickSaveRegister(View v) {
		// Save information in Service
		boolean validateField = validateFields();
		if (validateField) {
			if(!TextUtils.isEmpty(txtFullAddress.getText().toString())){
				finalAddress=txtFullAddress.getText().toString();
			}
			new SaveInformationDataBaseNew().execute();
		}
	}

	private class SaveInformationDataBaseNew extends
			AsyncTask<Void, Void, Void> {

		private ProgressDialog dialog = new ProgressDialog(
				RegisterComplientActivity.this);
		private String Content = "";

		@Override
		protected void onPreExecute() {
			dialog.setMessage(getString(R.string.messagesProcessDenounce));
			dialog.show();
		}

		@SuppressWarnings("deprecation")
		@Override
		protected Void doInBackground(Void... params) {
			System.out.println("onLoad!!!");
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(
					CommonConstants.URLService.REGISTER_COMPLAINT);
			post.setHeader("content-type", "application/json; charset=UTF-8");

			JSONObject dato = new JSONObject();
			try {

				dato.put(CommonConstants.RequestSaveComplaint.IDUSER_REQUEST_COMPLAINT,Integer.parseInt(idUserService));
				dato.put(CommonConstants.RequestSaveComplaint.LONGITUDE_REQUEST_COMPLAINT,finalLongitude);
				dato.put(CommonConstants.RequestSaveComplaint.LATITUDE_REQUEST_COMPLAINT,finalLatitude);
				dato.put(CommonConstants.RequestSaveComplaint.FULLADDRESS_REQUEST_COMPLAINT,UtilMethods.encriptValue(finalAddress));
				dato.put(CommonConstants.RequestSaveComplaint.COMMENT_ADITIONAL_REQUEST_COMPLAINT,txtComment.getText());
				dato.put(CommonConstants.RequestSaveComplaint.NUMBER_PLATE_REQUEST_COMPLAINT,txtNumberPlate.getText());
				dato.put(CommonConstants.RequestSaveComplaint.HEX_PHOTO_1_REQUEST_COMPLAINT,hexPhoto1);
				dato.put(CommonConstants.RequestSaveComplaint.HEX_PHOTO_2_REQUEST_COMPLAINT,hexPhoto2);
				dato.put(CommonConstants.RequestSaveComplaint.HEX_PHOTO_3_REQUEST_COMPLAINT,hexPhoto3);
				dato.put(CommonConstants.RequestSaveComplaint.CATEGORY_IMAGE_REQUEST_COMPLAINT,CommonConstants.GenericValues.CATEGORY_UPLOAD_IMAGE);
				dato.put(CommonConstants.RequestSaveComplaint.DISTRICT_REQUEST_COMPLAINT,finalDistrict);

				StringEntity entity = new StringEntity(dato.toString());
				post.setEntity(entity);

				HttpResponse resp = httpClient.execute(post);
				String respStr = EntityUtils.toString(resp.getEntity());
				System.out.println("La respues que viene : " + respStr);
				Content = respStr;
			} catch (Exception e) {
				// Close progress dialog
				dialog.dismiss();
				Toast.makeText(RegisterComplientActivity.this,"Hubo un error en el proceso de Registro de Denuncia ("+ e.getMessage()+ "). \nDisculpe las molestias.",
						Toast.LENGTH_LONG).show();
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
				System.out.println("codeResponse : " + codeResponse);
				int idComplaint = jObject.getInt("idComplient");
				if (CommonConstants.CodeResponse.RESPONSE_SUCCESS_COMPLAINT.equals(codeResponse)) {
					Intent i = new Intent(RegisterComplientActivity.this,SuccessRecordActivity.class);
					i.putExtra("idComplaint",getString(R.string.strMessagesCodeResponse) + " "+ idComplaint);
					startActivity(i);
				} else {
					Toast.makeText(RegisterComplientActivity.this,getString(R.string.errorSaveComplaint),Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(RegisterComplientActivity.this,"Hubo un error en la respuesta del Registro de la Denuncia ("+ e.getMessage() + "). Disculpe las molestias.",	Toast.LENGTH_LONG).show();
			}
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

	public boolean validateFields() {
		boolean validateResult = true;
		
		if(("0.0".equals(finalLatitude)||"".equals(finalLatitude)) && ("0.0".equals(finalLongitude))||"".equals(finalLongitude)){
			cboDistrict.setVisibility(View.VISIBLE);
			txtFullAddress.setVisibility(View.VISIBLE);
		}
		
		if (TextUtils.isEmpty(txtNumberPlate.getText())) {
			validateResult = false;
			txtNumberPlate.setError(getString(R.string.fieldRequired));
		}else if(txtFullAddress.isShown()&&TextUtils.isEmpty(txtFullAddress.getText())){
			validateResult = false;
			txtFullAddress.setError(getString(R.string.fieldRequired));
		}else if(cboDistrict.isShown()&&!selectedDistrict){
			validateResult = false;
			UtilMethods.alertbox(getString(R.string.titleError),getString(R.string.messagesValidationSelectDistrict) , RegisterComplientActivity.this,R.drawable.error);
		}else  if(!processImageComplete){
			validateResult = false;
			UtilMethods.alertbox(getString(R.string.titleError), getString(R.string.messagesValidationProccessImage), RegisterComplientActivity.this,R.drawable.error);
		}
		
		return validateResult;
	}

	public void processImage() throws Exception {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 5;
		if (!TextUtils.isEmpty(urlPhoto1)) {
			Uri startDir=Uri.parse(urlPhoto1);
			Bitmap b1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(startDir), null, options);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			b1.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			byte[] image1 = stream.toByteArray();
			hexPhoto1 = UtilMethods.bytesToHexString(image1);
		}
		if (!TextUtils.isEmpty(urlPhoto2)) {
			Uri startDir = Uri.parse(urlPhoto2);
			final Bitmap b2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(startDir), null, options);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			b2.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			byte[] image2 = stream.toByteArray();
			hexPhoto2 = UtilMethods.bytesToHexString(image2);
		}
		if (!TextUtils.isEmpty(urlPhoto3)) {
			Uri startDir = Uri.parse(urlPhoto3);
			final Bitmap b3 = BitmapFactory.decodeStream(getContentResolver().openInputStream(startDir), null, options);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			b3.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			byte[] image3 = stream.toByteArray();
			hexPhoto3 = UtilMethods.bytesToHexString(image3);
		}
	}
	
	@SuppressLint("NewApi")
	public void getPossitionAndAddres() {
		// create class object
		gps = new GPSTracker(RegisterComplientActivity.this);

		// check if GPS enabled
		if (gps.canGetLocation()) {

			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();

			lblLatitude.setText(latitude + "");
			lblLongitude.setText(longitude + "");
			finalLatitude = String.valueOf(latitude);
			finalLongitude = String.valueOf(longitude);
			Geocoder geo = new Geocoder(getApplicationContext(),
					Locale.getDefault());

			if (Geocoder.isPresent()) {
				try {
					List<Address> addresses = geo.getFromLocation(latitude,	longitude, 1);
					int i=0;
					for(Address beanAddress:addresses){
						String specificAddres="";
						if (beanAddress.getMaxAddressLineIndex() > 0) {
							specificAddres = beanAddress.getAddressLine(i);
						}
						if(i==0){
							finalDistrict=beanAddress.getLocality().toUpperCase();
						}
						listAddress.add(specificAddres+", "+ beanAddress.getLocality()+", "+beanAddress.getCountryName());
						i++;
						completAddres=true;
					}
					totalAddres=i;
					
				} catch (IOException e) {
					Toast.makeText(getApplicationContext(),"Ocurrio un error : " + e.getMessage(),Toast.LENGTH_LONG).show();
				}
			}
			
		} else {
			gps.showSettingsAlert();
		}
		listAddress.add("Agregar otra dirección");
	}
	
	
}
