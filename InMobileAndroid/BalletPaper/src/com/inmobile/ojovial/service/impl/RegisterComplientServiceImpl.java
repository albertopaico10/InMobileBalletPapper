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
import com.inmobile.ojovial.activity.RegisterComplientActivity;
import com.inmobile.ojovial.activity.SuccessRecordActivity;
import com.inmobile.ojovial.bean.ComplaintBean;
import com.inmobile.ojovial.bean.PhotoBean;
import com.inmobile.ojovial.bean.UserSqlLiteBean;
import com.inmobile.ojovial.service.RegisterComplientService;
import com.inmobile.ojovial.sql.DB_BalletPaper;
import com.inmobile.ojovial.util.CommonConstants;
import com.inmobile.ojovial.util.UtilMethods;

public class RegisterComplientServiceImpl implements RegisterComplientService {

	public String callServiceRegister(ComplaintBean complaintBean)throws Exception{
		System.out.println("onLoad!!!");
		String respStr="";
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(CommonConstants.URLService.REGISTER_COMPLAINT);
//		post.setHeader("content-type", "application/json; charset=UTF-8");
//		post.setHeader("Accept", "application/json");
		Map<String, Object> dato = new HashMap<String, Object>();
		System.out.println("antes de mapear datos");
//		try {
		dato.put(CommonConstants.RequestSaveComplaint.IDUSER_REQUEST_COMPLAINT,Integer.parseInt(complaintBean.getIdUserService()));
		dato.put(CommonConstants.RequestSaveComplaint.LONGITUDE_REQUEST_COMPLAINT,complaintBean.getLongitude());
		dato.put(CommonConstants.RequestSaveComplaint.LATITUDE_REQUEST_COMPLAINT,complaintBean.getLatitude());
		dato.put(CommonConstants.RequestSaveComplaint.FULLADDRESS_REQUEST_COMPLAINT,UtilMethods.encriptValue(complaintBean.getGpsCompleteAddress()));
		dato.put(CommonConstants.RequestSaveComplaint.COMMENT_ADITIONAL_REQUEST_COMPLAINT,complaintBean.getComment());
		dato.put(CommonConstants.RequestSaveComplaint.NUMBER_PLATE_REQUEST_COMPLAINT,complaintBean.getNumberPlate());
		dato.put(CommonConstants.RequestSaveComplaint.FILE_BYTE_1_REQUEST_COMPLAINT,complaintBean.getPhotoBean().getFileImage1());
		dato.put(CommonConstants.RequestSaveComplaint.FILE_BYTE_2_REQUEST_COMPLAINT,complaintBean.getPhotoBean().getFileImage2());
		dato.put(CommonConstants.RequestSaveComplaint.FILE_BYTE_3_REQUEST_COMPLAINT,complaintBean.getPhotoBean().getFileImage3());
		dato.put(CommonConstants.RequestSaveComplaint.CATEGORY_IMAGE_REQUEST_COMPLAINT,CommonConstants.GenericValues.CATEGORY_UPLOAD_IMAGE);
		dato.put(CommonConstants.RequestSaveComplaint.DISTRICT_REQUEST_COMPLAINT,complaintBean.getGpsDistrict());
		dato.put(CommonConstants.RequestSaveComplaint.ADDRESS_REQUEST_COMPLAINT,complaintBean.getGpsAddress());
		dato.put(CommonConstants.RequestSaveComplaint.COUNTRY_REQUEST_COMPLAINT,"Peru");
			
		String json = new GsonBuilder().create().toJson(dato, Map.class);
//		post.setEntity(new StringEntity(json));
		StringEntity entity = new StringEntity(json,"UTF-8");
		post.setEntity(entity);
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-Type", "application/json; charset=UTF-8");
		System.out.println("Antes de mandar al servicio...=)");
		HttpResponse resp = httpClient.execute(post);
		respStr = EntityUtils.toString(resp.getEntity());
		System.out.println("La respues que viene : " + respStr);
//		Content = respStr;
//		} catch (Exception e) {
//			linearLayoutForm.setVisibility(View.VISIBLE);
//			linearLayoutProgress.setVisibility(View.GONE);
//			Toast.makeText(RegisterComplientActivity.this,"Hubo un error en el proceso de Registro de Denuncia ("+ e.getMessage()+ "). \nDisculpe las molestias.",
//					Toast.LENGTH_LONG).show();
//		}
		return respStr;
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
//				Toast.makeText(gcontext,"Hubo un error en la respuesta de los ditritos("+ e.getMessage() + "). Disculpe las molestias.",Toast.LENGTH_LONG).show();
			}
		}


	}

	
}
