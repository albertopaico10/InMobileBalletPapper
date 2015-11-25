package com.inmobile.ojovial.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.inmobile.ojovial.R;
import com.inmobile.ojovial.service.CommonService;
import com.inmobile.ojovial.service.impl.CommonServiceImpl;
import com.inmobile.ojovial.sql.DB_BalletPaper;
import com.inmobile.ojovial.util.CommonConstants;
import com.inmobile.ojovial.util.GPSTracker;
import com.inmobile.ojovial.util.UtilMethods;

public class PrincipalMainActivity extends ActionBarActivity {
	
	CommonService commonService=new CommonServiceImpl();
	DB_BalletPaper dbBalletPaper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principalmenu);
		
		createAndroidDatase();
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
				PrincipalMainActivity.this, R.drawable.advertencia,CommonConstants.GenericValues.DIALOG_ALERT);
	}

	public void onClickMyInformation(View v) {
		UtilMethods.alertbox(getString(R.string.titleAdvertencia),
				getString(R.string.messagesOptionOnlyForWeb),
				PrincipalMainActivity.this, R.drawable.advertencia,CommonConstants.GenericValues.DIALOG_ALERT);
	}

	public void onClickRecommendation(View v) {
		UtilMethods.alertbox(getString(R.string.titleAdvertencia),
				getString(R.string.messagesOptionOnlyForWeb),
				PrincipalMainActivity.this, R.drawable.advertencia,CommonConstants.GenericValues.DIALOG_ALERT);
	}

	private void createAndroidDatase() {
		dbBalletPaper = new DB_BalletPaper(this, "DB_AndroidBalletPaper", null, 1);
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
		}else if(id == R.id.closeSession){
			//--update status in internal database
			commonService.desactiveUserInternalDataBase(dbBalletPaper);
			Intent i = new Intent(this, LoginActivity.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed(){
		Toast.makeText(getApplicationContext(),getString(R.string.notBackStepGeneral),Toast.LENGTH_LONG).show();
	}
	
}
