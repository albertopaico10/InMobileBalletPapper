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
}
