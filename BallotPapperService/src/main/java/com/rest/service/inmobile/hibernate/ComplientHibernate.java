package com.rest.service.inmobile.hibernate;

import java.util.List;

import com.rest.service.inmobile.hibernate.bean.Complaint;

public interface ComplientHibernate {

	public int saveComplient(Complaint beanComplient)throws Exception;
	public Complaint getComplaintByNumberPlate(String numberPlate)throws Exception;
	public List<Complaint> getComplaintByDistrict(String nameDistrict)throws Exception;
	public List<Complaint> getComplaintByUser(int idUser)throws Exception;
	public Complaint getComplaintByIdComplaint(int idCompaint)throws Exception;
	public Complaint saveComplientObject(Complaint beanComplient) throws Exception;
}
