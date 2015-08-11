package com.inmobile.ojovial.validation;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;

public class RegisterUserValidation {
	
	public static boolean isValidateRegisterUserFields(Context context,EditText registerEmail,EditText registerpassword
			,EditText registerpasswordConfirm,EditText registerName,EditText registerLastName
			,EditText registerDni,CheckBox registerAdult,CheckBox registerAceptTermin) {
		System.out.println("Metofo isValidateRegisterUserFields");
		boolean validateField = true;
		if (!Validation.isEmailAddress(context,registerEmail)) validateField = false;
		if (!Validation.isPasswordEqual(context,registerpassword,registerpasswordConfirm)) validateField = false;
		if (Validation.isEmpty(context,registerName)) validateField = false;
		if (Validation.isEmpty(context,registerLastName)) validateField = false;
		if (Validation.isEmpty(context,registerDni)) validateField = false;
		if (!Validation.isCheck(context, registerAdult)) validateField = false;
		if (!Validation.isCheck(context, registerAceptTermin)) validateField = false;
		return validateField;
	}
}
