package com.example.balletpaper.validation;

import com.example.balletpaper.R;
import com.example.balletpaper.RegisterComplientActivity;
import com.example.balletpaper.bean.ComplaintBean;
import com.example.balletpaper.bean.PhotoBean;
import com.example.balletpaper.util.UtilMethods;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

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
