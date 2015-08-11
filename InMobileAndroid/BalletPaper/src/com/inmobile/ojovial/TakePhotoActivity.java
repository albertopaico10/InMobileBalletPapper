package com.inmobile.ojovial;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class TakePhotoActivity extends ActionBarActivity {
	ImageButton btnPhotoPicture1;
	ImageView imgPhotoPicture1;
	ImageButton btnPhotoPicture2;
	ImageView imgPhotoPicture2;
	ImageButton btnPhotoPicture3;
	ImageView imgPhotoPicture3;
	Button btnNextPage;
	Uri fileUri = null;
	String rootFileImageN1 = "", rootFileImageN2 = "", rootFileImageN3 = "";
	
	File path=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.takephoto);
		btnPhotoPicture1 = (ImageButton) findViewById(R.id.idBtnTakePhotoN1);
		imgPhotoPicture1 = (ImageView) findViewById(R.id.idPhotoImageViewN1);
		btnPhotoPicture2 = (ImageButton) findViewById(R.id.idBtnTakePhotoN2);
		imgPhotoPicture2 = (ImageView) findViewById(R.id.idPhotoImageViewN2);
		btnPhotoPicture3 = (ImageButton) findViewById(R.id.idBtnTakePhotoN3);
		imgPhotoPicture3 = (ImageView) findViewById(R.id.idPhotoImageViewN3);
		btnNextPage=(Button) findViewById(R.id.btnNextPhoto);
		
		 path = Environment.getExternalStorageDirectory();
		
		if (savedInstanceState != null) {
			// Restore value of members from saved state
			rootFileImageN1=savedInstanceState.getString("rootFileImageN1");
			rootFileImageN2=savedInstanceState.getString("rootFileImageN2");
			rootFileImageN3=savedInstanceState.getString("rootFileImageN3");
		}
		
		if (!TextUtils.isEmpty(rootFileImageN1)) {
			try {
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 5;
				Uri startDir = Uri.parse(rootFileImageN1);
				final Bitmap b = BitmapFactory.decodeStream(getContentResolver().openInputStream(startDir), null,options);
				imgPhotoPicture1.setImageBitmap(b);
				imgPhotoPicture1.setVisibility(View.VISIBLE);
			} catch (FileNotFoundException e) {
				Toast.makeText(this, "Error in internal Process in Foto (1) : "+e.getMessage(),Toast.LENGTH_LONG).show();
			}
		}if(!TextUtils.isEmpty(rootFileImageN2)){
			try {
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 5;
				Uri startDir2 = Uri.parse(rootFileImageN2);
				final Bitmap b2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(startDir2), null,options);
				imgPhotoPicture2.setImageBitmap(b2);
				imgPhotoPicture2.setVisibility(View.VISIBLE);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this, "Error in internal Process in Foto (2) : "+e.getMessage(),Toast.LENGTH_LONG).show();
			}
		}if(!TextUtils.isEmpty(rootFileImageN3)){
			try {
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 5;
				Uri startDir3 = Uri.parse(rootFileImageN3);
				final Bitmap b3 = BitmapFactory.decodeStream(getContentResolver().openInputStream(startDir3), null,options);
				imgPhotoPicture3.setImageBitmap(b3);
				imgPhotoPicture3.setVisibility(View.VISIBLE);
			} catch (FileNotFoundException e) {
				Toast.makeText(this, "Error in internal Process in Foto (3) : "+e.getMessage(),Toast.LENGTH_LONG).show();
			}
		}

		btnPhotoPicture1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onTakePhoto1();
			}
		});

		btnPhotoPicture2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onTakePhoto2();
			}
		});

		btnPhotoPicture3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onTakePhoto3();
			}
		});
		
		btnNextPage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onNextPage();
			}
		});
		
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// savedInstanceState.putBoolean("MyBoolean", true);
		// savedInstanceState.putDouble("myDouble", 1.9);
		// savedInstanceState.putInt("MyInt", 1);
		savedInstanceState.putString("rootFileImageN1", rootFileImageN1);
		savedInstanceState.putString("rootFileImageN2", rootFileImageN2);
		savedInstanceState.putString("rootFileImageN3", rootFileImageN3);
		// etc.
		super.onSaveInstanceState(savedInstanceState);
	}

	public void onTakePhoto1() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT,"photoBalletPaper");
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 1);
		
		
	}

	public void onTakePhoto2() {
		System.out.println("ENTRE2.............!!!");
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 2);
	}

	public void onTakePhoto3() {
		System.out.println("ENTRE3.............!!!");
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 3);
	}
	
	public void onNextPage() {
		System.out.println("onNextPage.............!!!");
		if(validatePhoto()){
			Intent i = new Intent(TakePhotoActivity.this, RegisterComplientActivity.class);
			Bundle b = new Bundle();
			b.putString("photo1",rootFileImageN1);
			b.putString("photo2",rootFileImageN2);
			b.putString("photo3",rootFileImageN3);
			i.putExtras(b);
			
			startActivity(i);
		}else{
			Toast.makeText(this,getString(R.string.strUploadOnePhoto), Toast.LENGTH_LONG).show();
		}
		
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				Bitmap bp = (Bitmap) data.getExtras().get("data");
		        imgPhotoPicture1.setImageBitmap(bp);
		        imgPhotoPicture1.setVisibility(View.VISIBLE);
		        if(data.getDataString()==null){
		        	rootFileImageN1=editImageFromIntentNull(getApplicationContext(), bp);
		        }else{
		        	rootFileImageN1 = data.getDataString();
		        }
				Toast.makeText(this,getString(R.string.successTakePhoto), Toast.LENGTH_LONG).show();
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, getString(R.string.problemTakePhoto),Toast.LENGTH_LONG).show();
			}
		} else if (requestCode == 2) {
			if (resultCode == RESULT_OK) {
				Bitmap bp2 = (Bitmap) data.getExtras().get("data");
				imgPhotoPicture2.setImageBitmap(bp2);
				imgPhotoPicture2.setVisibility(View.VISIBLE);
				if(data.getDataString()==null){
			       	rootFileImageN2=editImageFromIntentNull(getApplicationContext(), bp2);
			    }else{
			     	rootFileImageN2 = data.getDataString();
			    }
				Toast.makeText(this,getString(R.string.successTakePhoto), Toast.LENGTH_LONG).show();
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, getString(R.string.problemTakePhoto),	Toast.LENGTH_LONG).show();
			}
		} else if (requestCode == 3) {
			if (resultCode == RESULT_OK) {
				Bitmap bp3 = (Bitmap) data.getExtras().get("data");
				imgPhotoPicture3.setImageBitmap(bp3);
				imgPhotoPicture3.setVisibility(View.VISIBLE);
				if(data.getDataString()==null){
			       	rootFileImageN3=editImageFromIntentNull(getApplicationContext(), bp3);
			    }else{
			     	rootFileImageN3 = data.getDataString();
			    }
				Toast.makeText(this,getString(R.string.successTakePhoto), Toast.LENGTH_LONG).show();
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, getString(R.string.problemTakePhoto),Toast.LENGTH_LONG).show();
			}
		}
	}
	
	public String editImageFromIntentNull(Context inContext, Bitmap inImage) {
		String path="";
		inImage = Bitmap.createScaledBitmap(inImage, 1024, 768, true);
	    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

	    path = Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
	    return path;
	}
	 
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		rootFileImageN1 = savedInstanceState.getString("rootFileImageN1");
		rootFileImageN2 = savedInstanceState.getString("rootFileImageN2");
		rootFileImageN3 = savedInstanceState.getString("rootFileImageN3");
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
	
	public boolean validatePhoto(){
		boolean validate=false;
		if(!TextUtils.isEmpty(rootFileImageN1)){
			validate=true;
		}else if(!TextUtils.isEmpty(rootFileImageN2)){
			validate=true;
		}else if(!TextUtils.isEmpty(rootFileImageN3)){
			validate=true;
		}
		return validate;
	}

}
