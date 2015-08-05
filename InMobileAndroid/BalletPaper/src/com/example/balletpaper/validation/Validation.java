package com.example.balletpaper.validation;

import android.content.Context;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.balletpaper.R;

public class Validation {
    
	public static boolean isEmailAddress(Context context,EditText editText) {
		boolean validationValue=true;
        if(!isEmpty(context,editText)){
        	if(!android.util.Patterns.EMAIL_ADDRESS.matcher(editText.getText()).matches()){
        		editText.setError(context.getString(R.string.emailFormat));
        		validationValue=false;
        	}
        }else{
        	validationValue=false;
        }
		return validationValue;
    }
	
	public static boolean isEmpty(Context context,EditText editText) {
		String errorRequired =  context.getString(R.string.fieldRequired);
		boolean validationValue=false;
		if(TextUtils.isEmpty(editText.getText().toString().trim())){
			editText.setError(errorRequired);
			validationValue=true;
		}
		return validationValue;
    }
	
	public static boolean isCheck(Context context,CheckBox chkBox) {
		boolean validationValue=true;
		if(!chkBox.isChecked()){
			validationValue=false;
			chkBox.setError(context.getString(R.string.fieldRequired));
		}
		return validationValue;
    }
	
	public static boolean isPasswordEqual(Context context,EditText password,EditText confirmPassword) {
		boolean validationValue=false;
		if(!isEmpty(context,password)){
			if(!isEmpty(context,confirmPassword)){
				if(password.getText().toString().equals(confirmPassword.getText().toString())){
					validationValue=true;
				}else{
					validationValue=false;
					confirmPassword.setError(context.getString(R.string.lblMessagesPassowrdNotCoincided));
				}
			}else{
				validationValue=false;
			}
		}else{
			validationValue=false;
		}
		return validationValue;
    }
}
