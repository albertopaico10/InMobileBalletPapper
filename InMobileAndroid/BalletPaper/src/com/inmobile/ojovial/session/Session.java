package com.inmobile.ojovial.session;

import com.inmobile.ojovial.util.CommonConstants;
import com.inmobile.ojovial.util.CommonConstants.GenericValues;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

	private SharedPreferences prefs;
	
	public Session(Context context) {
		prefs=PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public void setIdUserService(String idUserService){
		prefs.edit().putString(CommonConstants.GenericValues.IDUSERSERVICE, idUserService);
		prefs.edit().commit();
	}
}
