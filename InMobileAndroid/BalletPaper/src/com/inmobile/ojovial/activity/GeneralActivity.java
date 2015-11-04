package com.inmobile.ojovial.activity;

import com.inmobile.ojovial.sql.DB_BalletPaper;

import android.support.v7.app.ActionBarActivity;

public class GeneralActivity extends ActionBarActivity {
	private DB_BalletPaper dbBalletPaper;
	private boolean isLogin=false;
	public GeneralActivity(){
		System.out.println("Primero entro a la clase General");
		//--Create DataBase
		createAndroidDatase();
		System.out.println("Seguimos con alguien esta logueado");
		isLogin = isLoginIntoApplication();
		System.out.println("La respuesta es : "+isLogin);
	}
	
	private boolean isLoginIntoApplication() {
		System.out.println("Vemos si alguien esta logueado y....???");
		return dbBalletPaper.existUserLoginToApplication();
	}

	private void createAndroidDatase() {
		System.out.println("Trato de crear un contexto de Base de datos");
		dbBalletPaper = new DB_BalletPaper(GeneralActivity.this, "DB_AndroidBalletPaper", null, 1);
	}

	public boolean isLogin() {
		return isLogin;
	}
}
