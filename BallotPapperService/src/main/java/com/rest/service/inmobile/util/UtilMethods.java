package com.rest.service.inmobile.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.google.gson.Gson;

public class UtilMethods {

	
	public static String fromObjectToString(Object beanObject){
		Gson gson=new Gson();
		String strGson=gson.toJson(beanObject);
		return strGson;
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	public static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}
	
	public static String encriptValue(String value){
		String valueEncript="";
		byte[] byteValue=value.getBytes();
		valueEncript=bytesToHexString(byteValue);
		return valueEncript;
	}
	
	public static String descriptValue(String valueEncript){
		byte[] byteValue=hexStringToByteArray(valueEncript);
		String valueNormal=new String(byteValue);
		return valueNormal;
	}
	
	public static String convertFormatString(Date date,String formatTo){
		DateFormat df = new SimpleDateFormat(formatTo);
		String returnDate = df.format(date);
		return returnDate;
	}	
	
	public static String getStatusComplaint(int idStatus){
		String strStatus="";
		switch(idStatus){
			case 1:
				strStatus=CommonConstants.StatusComplaint.STATUS_OPEN;
				break;
			case 2:
				strStatus=CommonConstants.StatusComplaint.STATUS_CLOSE;
				break;
			case 3:
				strStatus=CommonConstants.StatusComplaint.STATUS_DUPLICATED;
				break;
			case 4:
				strStatus=CommonConstants.StatusComplaint.STATUS_PROGESS;
				break;
			default:
				strStatus=CommonConstants.StatusComplaint.STATUS_WITTHOUTSTATUS;
		}
		return strStatus;
	}
	
	public static String saveFileInServer(byte[] imgByte,String nameFile,String formatFile,String rootFile)throws Exception{
		BufferedImage bfi = null;
		rootFile=rootFile+"/"+nameFile+formatFile;
		File directory=new File(rootFile);
		 if (!directory.exists()) {
			 bfi = ImageIO.read(new ByteArrayInputStream(imgByte));
			 File outputfile = new File(rootFile);
			 try {
				ImageIO.write(bfi, CommonConstants.ImageParameter.FORMAT_PNG, outputfile);
			 } catch (IOException e) {
			 	System.out.println("Error : "+e.toString());
			 }
			 bfi.flush();
		 }		 
		 return nameFile+formatFile;
	}
	
	public static String getFinalValuesForEmail(String email,String identifyValue,String finalValue){
		return email.replace(identifyValue, finalValue);
	}
}
