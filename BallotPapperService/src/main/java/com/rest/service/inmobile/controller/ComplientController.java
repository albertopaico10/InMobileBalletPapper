package com.rest.service.inmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.service.inmobile.bean.complient.ComplientRequest;
import com.rest.service.inmobile.bean.complient.ComplientResponse;
import com.rest.service.inmobile.facade.ComplientManager;
import com.rest.service.inmobile.util.CommonConstants;

@Controller
public class ComplientController {

	@Autowired
	private ComplientManager complientManager;
	
	@RequestMapping(value = CommonConstants.ValueRequestMapping.SAVE_COMPLIENT,	method = RequestMethod.POST)
	public @ResponseBody ComplientResponse saveComplient(@RequestBody ComplientRequest beanRequest) {
		System.out.println("entre a saveComplient");
		ComplientResponse beanResponse=complientManager.saveComplient(beanRequest);
		return beanResponse;
	}
	
}
