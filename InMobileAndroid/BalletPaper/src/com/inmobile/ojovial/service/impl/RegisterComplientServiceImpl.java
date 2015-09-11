package com.inmobile.ojovial.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.inmobile.ojovial.R;
import com.inmobile.ojovial.SuccessRecordActivity;
import com.inmobile.ojovial.bean.ComplaintBean;
import com.inmobile.ojovial.bean.PhotoBean;
import com.inmobile.ojovial.bean.UserSqlLiteBean;
import com.inmobile.ojovial.service.RegisterComplientService;
import com.inmobile.ojovial.sql.DB_BalletPaper;
import com.inmobile.ojovial.util.CommonConstants;
import com.inmobile.ojovial.util.UtilMethods;

public class RegisterComplientServiceImpl implements RegisterComplientService {

	public static Context gcontext=null;
	public PhotoBean gPhotoBean=null;
	public ComplaintBean gComplaintBean;
	Spinner gCboDistrict;
	List<String> gList= new ArrayList<String>();
	String gFinalAddress="";
	public LinearLayout gLinearLayoutForm,gLinearLayoutProgress,gLinearLayoutProgressInformation;

	@Override
	public void callServiceAllDistrict(Context context,Spinner cboDistrict,List<String> list,String finalAddress) {
		gFinalAddress=finalAddress;
		gcontext=context;
//		gCboDistrict=cboDistrict;
//		gList=list;
		new getListDistrict().execute();
	}
	
	@Override
	public void proccesImage(Context context, PhotoBean photoBean) {
		gcontext=context;
		gPhotoBean=photoBean;
		new ProcessImage().execute();
	}
	
	@Override
	public void callServiceRegisterComplaint(Context context,ComplaintBean complaintBean
			,LinearLayout linearLayoutRegisterComplaint,LinearLayout linearLayoutProgress){
		gcontext=context;
		gComplaintBean=complaintBean;
		gLinearLayoutForm=linearLayoutRegisterComplaint;
		gLinearLayoutProgress=linearLayoutProgress;
		new SaveInformationDataBaseNew().execute();
	}
	
	private class getListDistrict extends AsyncTask<Void, Integer, Void> {
		private String Content = "";

		@Override
		protected void onPreExecute() {
			
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
            JSONObject jsonResponse;
            
            try {
            	 jsonResponse = new JSONObject(Content);
            	 System.out.println("1");
            	 JSONArray jsonMainNode = jsonResponse.optJSONArray("ubigeoBean");
            	 System.out.println("2");
            	 int lengthJsonArr = jsonMainNode.length();  
            	 System.out.println("3---"+lengthJsonArr);
//            	 for(int i=0; i < lengthJsonArr; i++) {
//            		 JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
//            		 String id       = jsonChildNode.optString("id").toString();
//                     String name     = jsonChildNode.optString("name").toString();
//                     gList.add(name);
//                 }
//            	 int position=gList.indexOf(gFinalAddress);
//            	 if(position==-1){
//            		 gCboDistrict.setSelection(0);	
//            	 }else{
//            		 gCboDistrict.setSelection(position);	 
//            	 }
            	 
			} catch (Exception e) {
				Toast.makeText(gcontext,"Hubo un error en la respuesta de los ditritos("+ e.getMessage() + "). Disculpe las molestias.",Toast.LENGTH_LONG).show();
			}
		}


	}
	
	private class ProcessImage extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {

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
			gPhotoBean.setFileImage1(image1);
//			gPhotoBean.setHexPhoto1(UtilMethods.bytesToHexString(image1));
		}
		if (!TextUtils.isEmpty(gPhotoBean.getUrlPhoto2())) {
			Uri startDir = Uri.parse(gPhotoBean.getUrlPhoto2());
			final Bitmap b2 = BitmapFactory.decodeStream(gcontext.getContentResolver().openInputStream(startDir), null, options);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			b2.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			byte[] image2 = stream.toByteArray();
			gPhotoBean.setFileImage2(image2);
//			gPhotoBean.setHexPhoto2(UtilMethods.bytesToHexString(image2));
		}
		if (!TextUtils.isEmpty(gPhotoBean.getUrlPhoto3())) {
			Uri startDir = Uri.parse(gPhotoBean.getUrlPhoto3());
			final Bitmap b3 = BitmapFactory.decodeStream(gcontext.getContentResolver().openInputStream(startDir), null, options);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			b3.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			byte[] image3 = stream.toByteArray();
			gPhotoBean.setFileImage3(image3);
//			gPhotoBean.setHexPhoto3(UtilMethods.bytesToHexString(image3));
		}
	}
	
	private class SaveInformationDataBaseNew extends AsyncTask<Void, Void, Void> {

		private String Content = "";

		@Override
		protected void onPreExecute() {
//			Toast.makeText(gcontext,"DATOS: \n idUser : "+Integer.parseInt(gComplaintBean.getIdUserService())
//					+"\n Longitud : "+gComplaintBean.getLongitude()
//					+"\n Latitude : "+gComplaintBean.getLatitude()
//					+"\n Address : "+UtilMethods.encriptValue(gComplaintBean.getGpsCompleteAddress())+"**"+gComplaintBean.getGpsCompleteAddress()
//					+"\n Comentarios : "+gComplaintBean.getComment()
//					+"\n Numero de Placa : "+gComplaintBean.getNumberPlate()
//					+"\n Categoria de Foto"+CommonConstants.GenericValues.CATEGORY_UPLOAD_IMAGE
//					+"\n Distrito : "+gComplaintBean.getGpsDistrict()
//					+"\n Direccion : "+gComplaintBean.getGpsAddress()
//					+"\n Pais : "+gComplaintBean.getGpsCountry(), Toast.LENGTH_LONG).show();
			gLinearLayoutProgress.setVisibility(View.VISIBLE);
			System.out.println("Post PRE");
		}

		@SuppressWarnings("deprecation")
		@Override
		protected Void doInBackground(Void... params) {
			System.out.println("onLoad!!!");
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(CommonConstants.URLService.REGISTER_COMPLAINT);
			post.setHeader("content-type", "application/json; charset=UTF-8");
			post.setHeader("Accept", "application/json");
			Map<String, Object> dato = new HashMap<String, Object>();
			System.out.println("antes de mapear datos");
			try {
				dato.put(CommonConstants.RequestSaveComplaint.IDUSER_REQUEST_COMPLAINT,Integer.parseInt(gComplaintBean.getIdUserService()));
				dato.put(CommonConstants.RequestSaveComplaint.LONGITUDE_REQUEST_COMPLAINT,gComplaintBean.getLongitude());
				dato.put(CommonConstants.RequestSaveComplaint.LATITUDE_REQUEST_COMPLAINT,gComplaintBean.getLatitude());
				dato.put(CommonConstants.RequestSaveComplaint.FULLADDRESS_REQUEST_COMPLAINT,UtilMethods.encriptValue(gComplaintBean.getGpsCompleteAddress()));
				dato.put(CommonConstants.RequestSaveComplaint.COMMENT_ADITIONAL_REQUEST_COMPLAINT,gComplaintBean.getComment());
				dato.put(CommonConstants.RequestSaveComplaint.NUMBER_PLATE_REQUEST_COMPLAINT,gComplaintBean.getNumberPlate());
				dato.put(CommonConstants.RequestSaveComplaint.FILE_BYTE_1_REQUEST_COMPLAINT,gComplaintBean.getPhotoBean().getFileImage1());
				dato.put(CommonConstants.RequestSaveComplaint.FILE_BYTE_2_REQUEST_COMPLAINT,gComplaintBean.getPhotoBean().getFileImage2());
				dato.put(CommonConstants.RequestSaveComplaint.FILE_BYTE_3_REQUEST_COMPLAINT,gComplaintBean.getPhotoBean().getFileImage3());
				dato.put(CommonConstants.RequestSaveComplaint.CATEGORY_IMAGE_REQUEST_COMPLAINT,CommonConstants.GenericValues.CATEGORY_UPLOAD_IMAGE);
				dato.put(CommonConstants.RequestSaveComplaint.DISTRICT_REQUEST_COMPLAINT,gComplaintBean.getGpsDistrict());
				dato.put(CommonConstants.RequestSaveComplaint.ADDRESS_REQUEST_COMPLAINT,gComplaintBean.getGpsAddress());
				dato.put(CommonConstants.RequestSaveComplaint.COUNTRY_REQUEST_COMPLAINT,"Peru");
				
				String json = new GsonBuilder().create().toJson(dato, Map.class);
				
				post.setEntity(new StringEntity(json));
				System.out.println("Antes de mandar al servicio...=)");
				HttpResponse resp = httpClient.execute(post);
				String respStr = EntityUtils.toString(resp.getEntity());
				System.out.println("La respues que viene : " + respStr);
				Content = respStr;
			} catch (Exception e) {
				gLinearLayoutForm.setVisibility(View.VISIBLE);
				gLinearLayoutProgress.setVisibility(View.GONE);
				Toast.makeText(gcontext,"Hubo un error en el proceso de Registro de Denuncia ("+ e.getMessage()+ "). \nDisculpe las molestias.",
						Toast.LENGTH_LONG).show();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			JSONObject jObject = null;

			try {
				jObject = new JSONObject(Content);
				String codeResponse = jObject.getString("codeResponse");
				System.out.println("codeResponse : " + codeResponse);
				int idComplaint = jObject.getInt("idComplient");
				Toast.makeText(gcontext,"ID de la denuncia : "+idComplaint,Toast.LENGTH_LONG).show();
				if (CommonConstants.CodeResponse.RESPONSE_SUCCESS_COMPLAINT.equals(codeResponse)) {
					Intent i = new Intent(gcontext,SuccessRecordActivity.class);
					i.putExtra("idComplaint",String.valueOf(idComplaint));
					gcontext.startActivity(i);
				} else {
					Toast.makeText(gcontext,gcontext.getString(R.string.errorSaveComplaint),Toast.LENGTH_LONG).show();
					gLinearLayoutProgress.setVisibility(View.GONE);
				}
			} catch (Exception e) {
				gLinearLayoutProgress.setVisibility(View.GONE);
				Toast.makeText(gcontext,"Hubo un error en la respuesta del Registro de la Denuncia ("+ e.getMessage() + "). Disculpe las molestias.",	Toast.LENGTH_LONG).show();
			}
		}

	}

	
}
