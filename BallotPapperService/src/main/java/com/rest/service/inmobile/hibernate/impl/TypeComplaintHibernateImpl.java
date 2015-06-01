package com.rest.service.inmobile.hibernate.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.service.inmobile.hibernate.TypeComplaintHibernate;
import com.rest.service.inmobile.hibernate.bean.TypeComplaint;

@Repository
public class TypeComplaintHibernateImpl implements TypeComplaintHibernate {

	@Autowired
	SessionFactory sessionfactory;
	
	public List<TypeComplaint> listAllTypeComplaint() throws Exception {
		String query="from TypeComplaint where status='1'";
		System.out.println("query : "+query);
		Session session=sessionfactory.openSession();
		
		List<TypeComplaint> listTypeComplaint=session.createQuery(query).list();
		System.out.println("Cantidad de filas : "+listTypeComplaint.size());
		
		return listTypeComplaint;
	}

	public TypeComplaint getTypeComplaint(int idTypeComplaint) throws Exception {
		TypeComplaint beanTypeComplaint=null;
		String query="from TypeComplaint where status='1' and id='"+idTypeComplaint+"'";
		System.out.println("query : "+query);
		Session session=sessionfactory.openSession();
		
		List<TypeComplaint> listTypeComplaint=session.createQuery(query).list();
		System.out.println("Cantidad de filas : "+listTypeComplaint.size());
		
		session.close();
		if(listTypeComplaint.size()>0){
			beanTypeComplaint=listTypeComplaint.get(0);
		}
		return beanTypeComplaint;
	}

}
