package com.rest.service.inmobile.util;

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
}
