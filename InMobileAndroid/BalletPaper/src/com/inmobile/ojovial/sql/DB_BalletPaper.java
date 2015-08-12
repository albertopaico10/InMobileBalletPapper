package com.inmobile.ojovial.sql;

import com.inmobile.ojovial.bean.UserSqlLiteBean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_BalletPaper extends SQLiteOpenHelper {

	public DB_BalletPaper(Context contexto, String nombre,CursorFactory factory, int version) {
		super(contexto, nombre, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS USER(id integer primary key,email VARCHAR,isActive VARCHAR,idUserService VARCHAR);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS USER");
		onCreate(db);
	}
	//--From This part to other Specific Class
	//--TODO
	public void insertUser(String email, String idUser) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put("email", email);
		contentValues.put("idUserService", idUser);
		contentValues.put("isActive", "N");

		db.insert("USER", null, contentValues);
		db.close();
	}

	public Cursor getUserActive() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from USER where isActive='Y'", null);
		db.close();
		return res;
	}
	
	public String getIdUserService() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from USER where isActive='Y'", null);
		res.moveToFirst();
		String idUserService = res.getString(res.getColumnIndex("idUserService"));
		db.close();
		return idUserService;
	}

	public boolean updateUserActive(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("isActive", "Y");
		db.update("USER", contentValues, "idUserService = ? ", new String[] { id });
		db.close();
		return true;
	}
	
	public boolean updateUserDesactive() {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("isActive", "N");
		String[] args = new String[]{"Y"};
		db.update("USER", contentValues,"isActive = ?",args);
		db.close();
		return true;
	}
	
	public UserSqlLiteBean getRecoverActiveUser(){
		UserSqlLiteBean beanUser=new UserSqlLiteBean();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from USER where isActive='Y'", null);
		if(res.moveToFirst()){
			do {
				System.out.println("Id SQL Lite : "+Integer.parseInt(res.getString(0))+"||"+res.getString(1)+"||"+res.getString(2)+"||"+res.getString(3));
				beanUser.setId(Integer.parseInt(res.getString(0)));
				beanUser.setEmail(res.getString(1));
				beanUser.setIsActive(res.getString(2));
				beanUser.setIdUserService(res.getString(3));
			} while (res.moveToNext());
		}
		return beanUser;
	}
	
	public boolean existIdUserService(String idUser) {
		boolean exist=false;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from USER where idUserService='"+idUser+"'", null);
		if(res.moveToFirst()){
			exist=true;
		}
		db.close();
		return exist;
	}
	
	public int cantidadRegistros(){
		String selectQuery = "SELECT * FROM USER";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int value=cursor.getCount();
        db.close();
        return value;
	}
}
