package com.rest.service.inmobile.facade;

import com.rest.service.inmobile.bean.complient.ComplientRequest;
import com.rest.service.inmobile.bean.complient.ComplientResponse;
import com.rest.service.inmobile.bean.complient.ListComplaintResponse;

public interface ComplientManager {

	public ComplientResponse saveComplient(ComplientRequest beanRequest);
	public ListComplaintResponse getListComplaintByDistrict(int idUser);
	public ListComplaintResponse getListComplaintByUser(int idUser);
	public ComplientResponse getComplaintByNumberPlate(String numberPlate);
	public ComplientResponse updateCompaintType(int idComplaint,int typeComplaint);
}
