package com.inmobile.ojovial.activity;

import com.inmobile.ojovial.R;
import com.inmobile.ojovial.R.id;
import com.inmobile.ojovial.R.layout;
import com.inmobile.ojovial.R.menu;
import com.inmobile.ojovial.util.CommonConstants;
import com.inmobile.ojovial.util.UtilMethods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SuccessRecordActivity extends ActionBarActivity {
	
	private TextView lblIdComplaint;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_success_record);
		lblIdComplaint=(TextView)findViewById(R.id.idTxtIdCode);
		String idComplaint="ES....";
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			idComplaint = extras.getString(CommonConstants.GenericValues.IDCOMPLIENT);
		}
		lblIdComplaint.setText(idComplaint);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onMakeOtherComplaint(View v) {
		Intent i = new Intent(SuccessRecordActivity.this, PrincipalMainActivity.class);
		startActivity(i);
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
	public void onBackPressed(){
		Toast.makeText(getApplicationContext(),getString(R.string.notBackStepGeneral),Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onUserInteraction(){
		System.out.println("Hizo Click en la aplicación");
		UtilMethods.resetDisconnectTimer(SuccessRecordActivity.this);
	}
	
	public void onResume(){
		super.onResume();
		System.out.println("No Hay actividad *** onResume");
		UtilMethods.resetDisconnectTimer(SuccessRecordActivity.this);
	}
	
	public void onStop(){
		super.onStop();
		System.out.println("No Hay actividad *** onStop");
		UtilMethods.stopDisconnectTimer();
		
	}
}