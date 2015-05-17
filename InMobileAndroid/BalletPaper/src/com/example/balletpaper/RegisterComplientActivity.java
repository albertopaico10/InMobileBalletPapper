package com.example.balletpaper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.example.balletpaper.sql.DB_BalletPaper;
import com.example.balletpaper.util.CommonConstants;
import com.example.balletpaper.util.GPSTracker;
import com.example.balletpaper.util.UtilMethods;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.test.UiThreadTest;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterComplientActivity extends ActionBarActivity implements LocationListener {
	
	private EditText txtNumberPlate;
	private EditText txtComment;
	private TextView lblLatitude;
	private TextView lblLongitude;
	private EditText txtFullAddress;
	GPSTracker gps;
	String responseComplient,responseImageService1="",responseImageService2="",responseImageService3="";
	int idComplaintService=0;
	String urlPhoto1="",urlPhoto2="",urlPhoto3="";
	String hexPhoto1="",hexPhoto2="",hexPhoto3="";
	String finalLatitude="",finalLongitude="";
	private String finalAddress="",idUserService="";
	private DB_BalletPaper dbBalletPaper;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registercomplient);
		txtComment = (EditText) findViewById(R.id.idTxtAditionalComment);
		txtNumberPlate = (EditText) findViewById(R.id.idTxtPlate);
		lblLatitude = (TextView) findViewById(R.id.idLblLatitude);
		lblLongitude = (TextView) findViewById(R.id.idLblLongitud);
		txtFullAddress = (EditText) findViewById(R.id.idTxtFullAddress);
		
		getPossitionAndAddres();
		
		getUserFromDataBaseAndroid();
		
		txtFullAddress.setText(finalAddress);
	}
	
	private void getUserFromDataBaseAndroid() {
		dbBalletPaper = new DB_BalletPaper(this, "DB_AndroidBalletPaper", null, 1);
		idUserService = dbBalletPaper.getIdUserService();
		
	}

	public void onClickSaveRegister(View v){
		//Save information in Service
		boolean validateField=validateFields();
		if(validateField){
			new SaveInformationDataBaseNew().execute();
		}
	}
	
	private class SaveInformationDataBaseNew extends AsyncTask<Void, Void, Void> {
		
		private ProgressDialog dialog = new ProgressDialog(RegisterComplientActivity.this);
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
			HttpPost post = new HttpPost(CommonConstants.URLService.REGISTER_COMPLAINT);
			post.setHeader("content-type", "application/json; charset=UTF-8");
			
			JSONObject dato = new JSONObject();
			try {
				
				dato.put(CommonConstants.RequestSaveComplaint.IDUSER_REQUEST_COMPLAINT, Integer.parseInt(idUserService));
				dato.put(CommonConstants.RequestSaveComplaint.LONGITUDE_REQUEST_COMPLAINT, finalLongitude);
				dato.put(CommonConstants.RequestSaveComplaint.LATITUDE_REQUEST_COMPLAINT, finalLatitude);
				dato.put(CommonConstants.RequestSaveComplaint.FULLADDRESS_REQUEST_COMPLAINT, UtilMethods.encriptValue(txtFullAddress.getText().toString()));
				dato.put(CommonConstants.RequestSaveComplaint.COMMENT_ADITIONAL_REQUEST_COMPLAINT, txtComment.getText());
				dato.put(CommonConstants.RequestSaveComplaint.NUMBER_PLATE_REQUEST_COMPLAINT,txtNumberPlate.getText());
				dato.put(CommonConstants.RequestSaveComplaint.HEX_PHOTO_1_REQUEST_COMPLAINT, hexPhoto1);
				dato.put(CommonConstants.RequestSaveComplaint.HEX_PHOTO_2_REQUEST_COMPLAINT, hexPhoto2);
				dato.put(CommonConstants.RequestSaveComplaint.HEX_PHOTO_3_REQUEST_COMPLAINT, hexPhoto3);
				dato.put(CommonConstants.RequestSaveComplaint.CATEGORY_IMAGE_REQUEST_COMPLAINT, CommonConstants.GenericValues.CATEGORY_UPLOAD_IMAGE);
				
				StringEntity entity = new StringEntity(dato.toString());
				post.setEntity(entity);
				
				HttpResponse resp = httpClient.execute(post);
				String respStr = EntityUtils.toString(resp.getEntity());
				System.out.println("La respues que viene : " + respStr);
				Content = respStr;
			} catch (Exception e) {
				// Close progress dialog
				dialog.dismiss();
				Toast.makeText(RegisterComplientActivity.this, "Hubo un error en el proceso de Registro de Denuncia ("+e.getMessage()+"). \nDisculpe las molestias.",Toast.LENGTH_LONG).show();
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
				int idComplaint=jObject.getInt("idComplient");
				if(CommonConstants.CodeResponse.RESPONSE_SUCCESS_COMPLAINT.equals(codeResponse)){
					Intent i = new Intent(RegisterComplientActivity.this, SuccessRecordActivity.class);
					i.putExtra("idComplaint",getString(R.string.strMessagesCodeResponse)+" "+idComplaint);
					startActivity(i);
				}else{
					Toast.makeText(RegisterComplientActivity.this, getString(R.string.errorSaveComplaint),Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(RegisterComplientActivity.this, "Hubo un error en la respuesta del Registro de la Denuncia ("+e.getMessage()+"). Disculpe las molestias.",Toast.LENGTH_LONG).show();
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

	public boolean validateFields(){
		boolean validateResult=true;
		if (TextUtils.isEmpty(txtNumberPlate.getText())) {
			validateResult = false;
			txtNumberPlate.setError(getString(R.string.fieldRequired));
		}
		if (TextUtils.isEmpty(txtFullAddress.getText())) {
			validateResult = false;
			txtFullAddress.setError(getString(R.string.fieldRequired));
		}
		return validateResult;
	}
	
	public void processImage()throws Exception{
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 24;
		if(!TextUtils.isEmpty(urlPhoto1)){
			Uri startDir = Uri.parse(urlPhoto1);
			final Bitmap b1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(startDir), null,options);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			b1.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			byte[] image1 = stream.toByteArray();
			hexPhoto1 = UtilMethods.bytesToHexString(image1);
		}
		if(!TextUtils.isEmpty(urlPhoto2)){
			Uri startDir = Uri.parse(urlPhoto2);
			final Bitmap b2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(startDir), null,options);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			b2.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			byte[] image2 = stream.toByteArray();
			hexPhoto2 = UtilMethods.bytesToHexString(image2);
		}
		if(!TextUtils.isEmpty(urlPhoto3)){
			Uri startDir = Uri.parse(urlPhoto3);
			final Bitmap b3 = BitmapFactory.decodeStream(getContentResolver().openInputStream(startDir), null,options);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			b3.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			byte[] image3 = stream.toByteArray();
			hexPhoto3 = UtilMethods.bytesToHexString(image3);
		}
	}
	
	@SuppressLint("NewApi")
	public void getPossitionAndAddres(){
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			urlPhoto1 = extras.getString("photo1");
			urlPhoto2 = extras.getString("photo2");
			urlPhoto3 = extras.getString("photo3");
		    try {
				processImage();
			} catch (Exception e) {
				Toast.makeText(RegisterComplientActivity.this, "Error convirtiendo archivos a hexadecimal : "+e.getMessage(),Toast.LENGTH_LONG).show();
			}
		}
		
		// create class object
		gps = new GPSTracker(RegisterComplientActivity.this);

		// check if GPS enabled
		if (gps.canGetLocation()) {

			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();

			// \n is for new line
//			Toast.makeText(
//					getApplicationContext(),
//					"Your Location is - \nLat: " + latitude + "\nLong: "
//							+ longitude, Toast.LENGTH_LONG).show();
			lblLatitude.setText(latitude+"");
			lblLongitude.setText(longitude+"");
			finalLatitude=String.valueOf(latitude);
			finalLongitude=String.valueOf(longitude);
			String addressSpecific="", addressLocaly="", addressCountry="";
			Geocoder geo=new Geocoder(getApplicationContext(), Locale.getDefault());

			if(Geocoder.isPresent())
			{
			 try {
			  List<Address> addresses = geo.getFromLocation(latitude, longitude, 1);
			  if (addresses != null && addresses.size() > 0) 
			  {
			   Address address = addresses.get(0);
			   if(address.getMaxAddressLineIndex() > 0){
				   addressSpecific=address.getAddressLine(0);
			   }
			   addressLocaly=address.getLocality();	
			   addressCountry=address.getCountryName();
			   }
			  } catch (IOException e) {
					Toast.makeText(getApplicationContext(),"Ocurrio un error : "+e.getMessage(), Toast.LENGTH_LONG).show();
			 }
			}
			finalAddress=addressSpecific+", "+addressLocaly+", "+addressCountry;
		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
	}
}
