package com.inmobile.ojovial.service;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.inmobile.ojovial.bean.LoginBean;
import com.inmobile.ojovial.sql.DB_BalletPaper;

public interface LoginService {
	public void callServiceLogin(Context context,DB_BalletPaper dbBalletPaper,LoginBean loginBean,
			LinearLayout linearLayoutForm,LinearLayout linearLayoutProgress);
}
