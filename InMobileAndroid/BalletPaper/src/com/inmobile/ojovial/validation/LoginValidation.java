package com.inmobile.ojovial.validation;

import android.content.Context;
import android.widget.EditText;

public class LoginValidation {

	public static boolean isValidateLoginFields(Context context,EditText emailLogin,EditText passwordLogin) {
		System.out.println("Metofo isValidateLoginFields");
		boolean validateField = true;
		if (!Validation.isEmailAddress(context,emailLogin)) validateField = false;
		if (Validation.isEmpty(context,passwordLogin)) validateField = false;
		return validateField;
	}
	
}
