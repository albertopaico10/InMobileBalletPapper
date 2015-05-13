package com.example.balletpaper;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.example.balletpaper.util.GPSTracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
	private TextView lblFullAddress;
	GPSTracker gps;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registercomplient);
		txtComment = (EditText) findViewById(R.id.idTxtAditionalComment);
		txtNumberPlate = (EditText) findViewById(R.id.idTxtPlate);
		lblLatitude = (TextView) findViewById(R.id.idLblLatitude);
		lblLongitude = (TextView) findViewById(R.id.idLblLongitud);
		lblFullAddress = (TextView) findViewById(R.id.idLblFullAddress);
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
			
			Geocoder geo=new Geocoder(getApplicationContext(), Locale.getDefault());

			String mylocation;
			if(Geocoder.isPresent())
			{
			 try {
			  List<Address> addresses = geo.getFromLocation(latitude, longitude, 1);
			  if (addresses != null && addresses.size() > 0) 
			  {
			   Address address = addresses.get(0);
			   String addressText = String.format("%s, %s, %s",
			      // If there's a street address, add it
			      address.getMaxAddressLineIndex() > 0 ?address.getAddressLine(0) : "",
			      // Locality is usually a city
			      address.getLocality(),
			      // The country of the address
			      address.getCountryName());
			   mylocation="Lattitude: "+latitude+" Longitude: "+longitude+"\nAddress: "+addressText;
			   lblFullAddress.setText("Data Complete :"+mylocation);
			   }
			  } catch (IOException e) {
					Toast.makeText(getApplicationContext(),"Ocurrio un error : "+e.getMessage(), Toast.LENGTH_LONG).show();
			 }
			}
			
		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
	}
	
	public void onClickSaveRegister(View v){
		Intent i = new Intent(this, SuccessRecordActivity.class);
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
}
