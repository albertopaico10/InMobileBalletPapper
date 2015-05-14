package com.rest.service.inmobile.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.service.inmobile.hibernate.ImageHibernate;
import com.rest.service.inmobile.hibernate.bean.Image;

@Repository
public class ImageHibernateImpl implements ImageHibernate {
	
	@Autowired
	SessionFactory sessionfactory;

	public int saveImageId(Image imageBean) throws Exception {
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		session.saveOrUpdate(imageBean);
		System.out.println("Last ID : "+imageBean.getId());
		
		transaction.commit();
		session.close();
		return imageBean.getId();
	}

}
