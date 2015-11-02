package com.inmobile.ojovial.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.inmobile.ojovial.R;
import com.inmobile.ojovial.R.drawable;
import com.inmobile.ojovial.R.id;
import com.inmobile.ojovial.R.layout;
import com.inmobile.ojovial.R.menu;
import com.inmobile.ojovial.R.string;
import com.inmobile.ojovial.util.GPSTracker;
import com.inmobile.ojovial.util.UtilMethods;

public class PrincipalMainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principalmenu);
	}

	public void onClickRedirectCamera(View v) {
		// create class object
		GPSTracker gps = new GPSTracker(PrincipalMainActivity.this);
		// check if GPS enabled
		if (!gps.canGetLocation()) {
			gps.showSettingsAlert();
		}else{
			Intent i = new Intent(this, TakePhotoActivity.class);
			startActivity(i);
		}
		
	}

	public void onClickMyDenounce(View v) {
		UtilMethods.alertbox(getString(R.string.titleAdvertencia),
				getString(R.string.messagesOptionOnlyForWeb),
				PrincipalMainActivity.this, R.drawable.advertencia);
	}

	public void onClickMyInformation(View v) {
		UtilMethods.alertbox(getString(R.string.titleAdvertencia),
				getString(R.string.messagesOptionOnlyForWeb),
				PrincipalMainActivity.this, R.drawable.advertencia);
	}

	public void onClickRecommendation(View v) {
		UtilMethods.alertbox(getString(R.string.titleAdvertencia),
				getString(R.string.messagesOptionOnlyForWeb),
				PrincipalMainActivity.this, R.drawable.advertencia);
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
