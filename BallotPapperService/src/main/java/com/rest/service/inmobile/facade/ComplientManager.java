package com.rest.service.inmobile.facade;

import com.rest.service.inmobile.bean.complient.ComplientRequest;
import com.rest.service.inmobile.bean.complient.ComplientResponse;

public interface ComplientManager {

	public ComplientResponse saveComplient(ComplientRequest beanRequest);
	
}
