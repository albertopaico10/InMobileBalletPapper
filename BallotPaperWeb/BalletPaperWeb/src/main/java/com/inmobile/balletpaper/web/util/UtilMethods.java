package com.inmobile.balletpaper.web.util;

import com.google.gson.Gson;

public class UtilMethods {
	
	public static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}
	
	public static String fromObjectToString(Object beanObject) {
		Gson gson = new Gson();
		String strGson = gson.toJson(beanObject);
		return strGson;
	}
	
	public static String encriptValue(String value){
		String valueEncript="";
		byte[] byteValue=value.getBytes();
		valueEncript=bytesToHexString(byteValue);
		return valueEncript;
	}
}
