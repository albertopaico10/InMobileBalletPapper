package com.rest.service.inmobile.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.service.inmobile.hibernate.ComplientHibernate;
import com.rest.service.inmobile.hibernate.bean.Complaint;

@Repository
public class ComplientHibernateImpl implements ComplientHibernate {

	@Autowired
	SessionFactory sessionfactory;
	
	public int saveComplient(Complaint beanComplient) throws Exception {
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		session.saveOrUpdate(beanComplient);
		System.out.println("Last ID : "+beanComplient.getId());
		
		transaction.commit();
		session.close();
		return beanComplient.getId();
	}

}
