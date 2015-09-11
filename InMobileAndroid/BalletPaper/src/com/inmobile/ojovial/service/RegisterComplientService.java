package com.inmobile.ojovial.service;

import java.util.List;

import com.inmobile.ojovial.bean.ComplaintBean;
import com.inmobile.ojovial.bean.PhotoBean;
import com.inmobile.ojovial.sql.DB_BalletPaper;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

public interface RegisterComplientService {

	public void callServiceAllDistrict(Context context,Spinner cboDistrict,List<String> List,String finalAddress);
	public void proccesImage(Context context,PhotoBean photoBean);
	public void callServiceRegisterComplaint(Context context,ComplaintBean complaintBean,
			LinearLayout linearLayoutRegisterComplaint,LinearLayout linearLayoutProgress);
}
