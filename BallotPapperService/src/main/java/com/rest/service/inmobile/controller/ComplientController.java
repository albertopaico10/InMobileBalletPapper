package com.rest.service.inmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.service.inmobile.bean.complient.ComplientRequest;
import com.rest.service.inmobile.bean.complient.ComplientResponse;
import com.rest.service.inmobile.bean.complient.ListComplaintResponse;
import com.rest.service.inmobile.bean.ubigeo.UbigeoResponse;
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
	
	@RequestMapping(value = CommonConstants.ValueRequestMapping.LIST_COMPLAINT_BY_DISTRICT, method = RequestMethod.GET)
	public @ResponseBody ListComplaintResponse getListDistrict(@PathVariable("idUser") int idUser) {
		ListComplaintResponse beanResponse=complientManager.getListComplaintByDistrict(idUser);
		return beanResponse;
	}
	
	@RequestMapping(value = CommonConstants.ValueRequestMapping.LIST_COMPLAINT_BY_USER, method = RequestMethod.GET)
	public @ResponseBody ListComplaintResponse getListCompliantByUser(@PathVariable("idUser") int idUser) {
		ListComplaintResponse beanResponse=complientManager.getListComplaintByUser(idUser);
		return beanResponse;
	}
	
	@RequestMapping(value = CommonConstants.ValueRequestMapping.LIST_COMPLAINT_BY_PLATE, method = RequestMethod.GET)
	public @ResponseBody ComplientResponse getListByNumberPlate(@PathVariable("numberPlate") String numberPlate) {
		ComplientResponse beanResponse=complientManager.getComplaintByNumberPlate(numberPlate);
		return beanResponse;
	}
	
	@RequestMapping(value = CommonConstants.ValueRequestMapping.UPDATE_TYPE_COMPLAINT, method = RequestMethod.GET)
	public @ResponseBody ComplientResponse updateStatusComplaint(@PathVariable("idComplaint") int idComplaint,@PathVariable("typeComplaint") int typeComplaint) {
		ComplientResponse beanResponse=complientManager.updateCompaintType(idComplaint,typeComplaint);
		return beanResponse;
	}
	
}
