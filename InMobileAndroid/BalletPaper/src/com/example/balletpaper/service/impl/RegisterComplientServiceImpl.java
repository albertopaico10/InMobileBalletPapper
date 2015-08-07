package com.example.balletpaper.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.balletpaper.R;
import com.example.balletpaper.RegisterComplientActivity;
import com.example.balletpaper.SuccessRecordActivity;
import com.example.balletpaper.bean.ComplaintBean;
import com.example.balletpaper.bean.PhotoBean;
import com.example.balletpaper.service.RegisterComplientService;
import com.example.balletpaper.sql.DB_BalletPaper;
import com.example.balletpaper.util.CommonConstants;
import com.example.balletpaper.util.UtilMethods;

public class RegisterComplientServiceImpl implements RegisterComplientService {

	public static Context gcontext=null;
	public PhotoBean gPhotoBean=null;
	public ComplaintBean gComplaintBean;
	public DB_BalletPaper gDbBalletPaper;
	Spinner gCboDistrict;
	List<String> gList= new ArrayList<String>();
	String gFinalAddress="";
	
	
	@Override
	public void callServiceAllDistrict(Context context,Spinner cboDistrict,List<String> list,String finalAddress) {
		gFinalAddress=finalAddress;
		gcontext=context;
		gCboDistrict=cboDistrict;
		gList=list;
		new getListDistrict().execute();
	}
	
	@Override
	public void proccesImage(Context context, PhotoBean photoBean) {
		gcontext=context;
		gPhotoBean=photoBean;
		new ProcessImage().execute();
	}
	
	@Override
	public void processAditional(Context context,ComplaintBean complaintBean,DB_BalletPaper dbBalletPaper) {
		gComplaintBean=complaintBean;
		gDbBalletPaper=dbBalletPaper;
		new ProccesAdditionalInformation().execute();
	}
	
	public void callServiceRegisterComplaint(Context context,ComplaintBean complaintBean){
		gcontext=context;
		gComplaintBean=complaintBean;
		new SaveInformationDataBaseNew().execute();
	}
	
	private class getListDistrict extends AsyncTask<Void, Void, Void> {
		private ProgressDialog dialog = new ProgressDialog(gcontext);
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
//            	 districtArray=new String[lengthJsonArr];
            	 System.out.println("3---"+lengthJsonArr);
            	 for(int i=0; i < lengthJsonArr; i++) {
            		 JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
            		 String id       = jsonChildNode.optString("id").toString();
                     String name     = jsonChildNode.optString("name").toString();
                     gList.add(name);
                 }
            	 int position=gList.indexOf(gFinalAddress);
            	 if(position==-1){
            		 gCboDistrict.setSelection(0);	
            	 }else{
            		 gCboDistrict.setSelection(position);	 
            	 }
            	 
			} catch (Exception e) {
				Toast.makeText(gcontext,"Hubo un error en la respuesta de los ditritos("+ e.getMessage() + "). Disculpe las molestias.",Toast.LENGTH_LONG).show();
			}
            System.out.println("CHAUUuuu");
		}


	}
	
	private class ProcessImage extends AsyncTask<Void, Void, Void> {
		private ProgressDialog dialog = new ProgressDialog(gcontext);

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
				Toast.makeText(gcontext,"Error convirtiendo archivos a hexadecimal : "+ e.getMessage(), Toast.LENGTH_LONG).show();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			gPhotoBean.setCompleteProcessImage(true);
			Toast.makeText(gcontext,"Se completo el proceso de tratamiento de las fotos. \n Gracias", Toast.LENGTH_LONG).show();
			dialog.dismiss();
		}
		
	}
	
	private void processImage() throws Exception {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 5;
		if (!TextUtils.isEmpty(gPhotoBean.getUrlPhoto1())) {
			Uri startDir=Uri.parse(gPhotoBean.getUrlPhoto1());
			Bitmap b1 = BitmapFactory.decodeStream(gcontext.getContentResolver().openInputStream(startDir), null, options);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			b1.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			byte[] image1 = stream.toByteArray();
			gPhotoBean.setHexPhoto1(UtilMethods.bytesToHexString(image1));
		}
		if (!TextUtils.isEmpty(gPhotoBean.getUrlPhoto2())) {
			Uri startDir = Uri.parse(gPhotoBean.getUrlPhoto2());
			final Bitmap b2 = BitmapFactory.decodeStream(gcontext.getContentResolver().openInputStream(startDir), null, options);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			b2.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			byte[] image2 = stream.toByteArray();
			gPhotoBean.setHexPhoto2(UtilMethods.bytesToHexString(image2));
		}
		if (!TextUtils.isEmpty(gPhotoBean.getUrlPhoto3())) {
			Uri startDir = Uri.parse(gPhotoBean.getUrlPhoto3());
			final Bitmap b3 = BitmapFactory.decodeStream(gcontext.getContentResolver().openInputStream(startDir), null, options);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			b3.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			byte[] image3 = stream.toByteArray();
			gPhotoBean.setHexPhoto3(UtilMethods.bytesToHexString(image3));
		}
	}
	
	private class ProccesAdditionalInformation extends AsyncTask<Void, Void, Void> {
		private ProgressDialog dialog = new ProgressDialog(gcontext);

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
	
	private void getUserFromDataBaseAndroid() {
		gDbBalletPaper = new DB_BalletPaper(gcontext, "DB_AndroidBalletPaper", null,1);
		gComplaintBean.setIdUserService(gDbBalletPaper.getIdUserService());

	}
	
	private class SaveInformationDataBaseNew extends AsyncTask<Void, Void, Void> {

		private ProgressDialog dialog = new ProgressDialog(gcontext);
		private String Content = "";

		@Override
		protected void onPreExecute() {
			dialog.setMessage(gcontext.getString(R.string.messagesProcessDenounce));
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

				dato.put(CommonConstants.RequestSaveComplaint.IDUSER_REQUEST_COMPLAINT,Integer.parseInt(gComplaintBean.getIdUserService()));
				dato.put(CommonConstants.RequestSaveComplaint.LONGITUDE_REQUEST_COMPLAINT,gComplaintBean.getLongitude());
				dato.put(CommonConstants.RequestSaveComplaint.LATITUDE_REQUEST_COMPLAINT,gComplaintBean.getLatitude());
				dato.put(CommonConstants.RequestSaveComplaint.FULLADDRESS_REQUEST_COMPLAINT,UtilMethods.encriptValue(gComplaintBean.getAlternativeAddress()));
				dato.put(CommonConstants.RequestSaveComplaint.COMMENT_ADITIONAL_REQUEST_COMPLAINT,gComplaintBean.getComment());
				dato.put(CommonConstants.RequestSaveComplaint.NUMBER_PLATE_REQUEST_COMPLAINT,gComplaintBean.getNumberPlate());
				dato.put(CommonConstants.RequestSaveComplaint.HEX_PHOTO_1_REQUEST_COMPLAINT,gComplaintBean.getPhotoBean().getHexPhoto1());
				dato.put(CommonConstants.RequestSaveComplaint.HEX_PHOTO_2_REQUEST_COMPLAINT,gComplaintBean.getPhotoBean().getHexPhoto2());
				dato.put(CommonConstants.RequestSaveComplaint.HEX_PHOTO_3_REQUEST_COMPLAINT,gComplaintBean.getPhotoBean().getHexPhoto3());
				dato.put(CommonConstants.RequestSaveComplaint.CATEGORY_IMAGE_REQUEST_COMPLAINT,CommonConstants.GenericValues.CATEGORY_UPLOAD_IMAGE);
				dato.put(CommonConstants.RequestSaveComplaint.DISTRICT_REQUEST_COMPLAINT,gComplaintBean.getDistrict());

				StringEntity entity = new StringEntity(dato.toString());
				post.setEntity(entity);

				HttpResponse resp = httpClient.execute(post);
				String respStr = EntityUtils.toString(resp.getEntity());
				System.out.println("La respues que viene : " + respStr);
				Content = respStr;
			} catch (Exception e) {
				// Close progress dialog
				dialog.dismiss();
				Toast.makeText(gcontext,"Hubo un error en el proceso de Registro de Denuncia ("+ e.getMessage()+ "). \nDisculpe las molestias.",
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
					Intent i = new Intent(gcontext,SuccessRecordActivity.class);
					i.putExtra("idComplaint",gcontext.getString(R.string.strMessagesCodeResponse) + " "+ idComplaint);
					gcontext.startActivity(i);
				} else {
					Toast.makeText(gcontext,gcontext.getString(R.string.errorSaveComplaint),Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(gcontext,"Hubo un error en la respuesta del Registro de la Denuncia ("+ e.getMessage() + "). Disculpe las molestias.",	Toast.LENGTH_LONG).show();
			}
		}

	}

	
}
