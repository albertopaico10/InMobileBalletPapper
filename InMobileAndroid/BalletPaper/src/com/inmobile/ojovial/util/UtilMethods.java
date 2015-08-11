package com.inmobile.ojovial.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class UtilMethods {
	public static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}

	public static String encryptIt(String value) {
		try {
			DESKeySpec keySpec = new DESKeySpec(
					CommonConstants.EncriptedValues.KEY_VALUE_ENCRIPTED
							.getBytes("UTF8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(keySpec);

			byte[] clearText = value.getBytes("UTF8");
			// Cipher is not thread safe
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			String encrypedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);
			return encrypedValue;

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static String encriptValue(String value){
		String valueEncript="";
		byte[] byteValue=value.getBytes();
		valueEncript=bytesToHexString(byteValue);
		return valueEncript;
	}
	
	public static void alertbox(String title, String message,Activity activiy, int idIcon) {
	    final AlertDialog alertDialog = new AlertDialog.Builder(activiy).create();
	    alertDialog.setTitle(title);
	    alertDialog.setMessage(message);
	    alertDialog.setIcon(idIcon);
	    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	          public void onClick(DialogInterface dialog, int which) {
	              alertDialog.cancel();      
	        } });
	    alertDialog.show();
	 }
	
	public static void alertbox(String title, String message,Context activiy, int idIcon) {
	    final AlertDialog alertDialog = new AlertDialog.Builder(activiy).create();
	    alertDialog.setTitle(title);
	    alertDialog.setMessage(message);
	    alertDialog.setIcon(idIcon);
	    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	          public void onClick(DialogInterface dialog, int which) {
	              alertDialog.cancel();      
	        } });
	    alertDialog.show();
	 }
	
	public static void hideKeyboard(View v,Context c) {   
	    // Check if no view has focus:
	    View view = v;
	    if (view != null) {
	        InputMethodManager inputManager = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
	        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	    }
	}
	

}