package com.rest.service.inmobile.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.service.inmobile.hibernate.UserHibernate;
import com.rest.service.inmobile.hibernate.bean.User;
@Repository
public class UserHibernateImpl implements UserHibernate {
	
	@Autowired
	SessionFactory sessionfactory;

	public int saveUserResponseId(User userBean) throws Exception {

		Session session = sessionfactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(userBean);
		System.out.println("Last ID : " + userBean.getId());

		transaction.commit();
		session.close();
		return userBean.getId();
	}

}
