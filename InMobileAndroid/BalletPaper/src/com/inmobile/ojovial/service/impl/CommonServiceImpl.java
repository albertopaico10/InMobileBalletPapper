package com.inmobile.ojovial.service.impl;

import com.inmobile.ojovial.service.CommonService;
import com.inmobile.ojovial.sql.DB_BalletPaper;

public class CommonServiceImpl implements CommonService{

	public void desactiveUserInternalDataBase(DB_BalletPaper balletPaper){
		balletPaper.updateUserDesactive();
	}
}
