package com.example.balletpaper.service;

import java.util.List;

import com.example.balletpaper.bean.ComplaintBean;
import com.example.balletpaper.bean.PhotoBean;
import com.example.balletpaper.sql.DB_BalletPaper;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

public interface RegisterComplientService {

	public void callServiceAllDistrict(Context context,Spinner cboDistrict,List<String> List,String finalAddress);
	public void proccesImage(Context context,PhotoBean photoBean
			,LinearLayout linearLayoutProgress,ProgressBar processBar);
	public void processAditional(Context context,ComplaintBean complaintBean,DB_BalletPaper dbBalletPaper);
	public void callServiceRegisterComplaint(Context context,ComplaintBean complaintBean,
			LinearLayout linearLayoutRegisterComplaint,LinearLayout linearLayoutProgress);
}
