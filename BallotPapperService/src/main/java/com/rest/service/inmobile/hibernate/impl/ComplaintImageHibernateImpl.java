package com.rest.service.inmobile.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.service.inmobile.hibernate.ComplaintImageHibernate;
import com.rest.service.inmobile.hibernate.bean.ComplaintImage;

@Repository
public class ComplaintImageHibernateImpl implements ComplaintImageHibernate {

	@Autowired
	SessionFactory sessionfactory;
	
	public int saveComplientImage(ComplaintImage beanComplient) throws Exception {
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		session.saveOrUpdate(beanComplient);
		System.out.println("Last ID : "+beanComplient.getId());
		
		transaction.commit();
		session.close();
		return beanComplient.getId();
	}


}
