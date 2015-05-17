package com.rest.service.inmobile.hibernate.impl;

import java.util.List;

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

	public boolean existEmail(String email)throws Exception{
		boolean validateEmail=false;
		String query="from User where email='"+email+"'";
		System.out.println("query : "+query);
		Session session=sessionfactory.openSession();
		
		List<User> listSpecificById=session.createQuery(query).list();
		System.out.println("Cantidad de filas : "+listSpecificById.size());
		
		session.close();
		if(listSpecificById.size()>0){
			validateEmail=true;
		}				
		return validateEmail;
	}
	
	public User validateUser(String email, String password) throws Exception {
		User userBean=null;
		String query="from User where email='"+email+"' and passwordUser='"+password+"'";
		System.out.println("query : "+query);
		Session session=sessionfactory.openSession();
		
		List<User> listUserSpecific=session.createQuery(query).list();
		System.out.println("Cantidad de filas : "+listUserSpecific.size());
		
		session.close();
		if(listUserSpecific.size()>0){
			userBean=listUserSpecific.get(0);
		}
		return userBean;
	}
	
	public User getUser(String idUser) throws Exception {
		User userBean=null;
		String query="from User where status='1' and id='"+idUser+"'";
		System.out.println("query : "+query);
		Session session=sessionfactory.openSession();
		
		List<User> listUserSpecific=session.createQuery(query).list();
		System.out.println("Cantidad de filas : "+listUserSpecific.size());
		
		session.close();
		if(listUserSpecific.size()>0){
			userBean=listUserSpecific.get(0);
		}
		return userBean;
	}

}
