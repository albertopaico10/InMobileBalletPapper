package com.inmobile.ojovial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SuccessRecordActivity extends ActionBarActivity {
	
	private TextView lblIdComplaint;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.successrecord);
		lblIdComplaint=(TextView)findViewById(R.id.idTxtIdCode);
		String idComplaint="";
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			idComplaint = extras.getString("idComplaint");
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
}