package com.rest.service.inmobile.hibernate;

import com.rest.service.inmobile.hibernate.bean.Complaint;

public interface ComplientHibernate {

	public int saveComplient(Complaint beanComplient)throws Exception;
	
}
