package com.rest.service.inmobile.hibernate;

import java.util.List;

import com.rest.service.inmobile.hibernate.bean.TypeComplaint;

public interface TypeComplaintHibernate {

	public List<TypeComplaint> listAllTypeComplaint()throws Exception;
	public TypeComplaint getTypeComplaint(int idTypeComplaint)throws Exception;
	
}
