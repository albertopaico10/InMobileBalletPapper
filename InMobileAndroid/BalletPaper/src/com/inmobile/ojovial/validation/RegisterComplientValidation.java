package com.inmobile.ojovial.validation;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.inmobile.ojovial.R;
import com.inmobile.ojovial.bean.ComplaintBean;
import com.inmobile.ojovial.bean.PhotoBean;
import com.inmobile.ojovial.util.UtilMethods;

public class RegisterComplientValidation {

	public static boolean isValidateRegisterComplient(Context context,EditText txtNumberPlate,EditText txtComment
			,EditText txtFullAddress,Spinner cboDistrict,Spinner cboSpecificAddress,PhotoBean photoBean,
			ComplaintBean complaintBean){
		System.out.println("Metofo isValidateRegisterComplient");
		boolean validateField = true;
		if(Validation.isNotGetPossitionString(context,complaintBean.getLatitude())&&
				Validation.isNotGetPossitionString(context,complaintBean.getLongitude())){
			cboDistrict.setVisibility(View.VISIBLE);
			txtFullAddress.setVisibility(View.VISIBLE);
		}
		if(Validation.isEmpty(context, txtNumberPlate)){
			validateField=false;
		}
		else if(Validation.isShowValue(context, txtFullAddress)){
			validateField=false;
		}
		else if(cboDistrict.isShown()&&!complaintBean.isSelectedDistrict()){
			validateField=false;
			UtilMethods.alertbox(context.getString(R.string.titleError),context.getString(R.string.messagesValidationSelectDistrict) , context,R.drawable.error);
		}else if(!photoBean.isCompleteProcessImage()){
			validateField=false;
			UtilMethods.alertbox(context.getString(R.string.titleError),context.getString(R.string.messagesValidationProccessImage) , context,R.drawable.error);
		}
		return validateField;
	}
	
}
