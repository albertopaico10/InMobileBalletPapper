package com.rest.service.inmobile.util;

import com.google.gson.Gson;

public class UtilMethods {

	
	public static String fromObjectToString(Object beanObject){
		Gson gson=new Gson();
		String strGson=gson.toJson(beanObject);
		return strGson;
	}
}
