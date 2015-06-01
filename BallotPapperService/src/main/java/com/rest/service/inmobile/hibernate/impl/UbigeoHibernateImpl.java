package com.rest.service.inmobile.hibernate.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.service.inmobile.hibernate.UbigeoHibernate;
import com.rest.service.inmobile.hibernate.bean.District;
@Repository
public class UbigeoHibernateImpl implements UbigeoHibernate{

	@Autowired
	SessionFactory sessionfactory;
	
	public List<District> listDistrict(int idProvince) throws Exception {
		String query="from District where provinceId='"+idProvince+"'";
		System.out.println("query : "+query);
		Session session=sessionfactory.openSession();
		
		List<District> listDistrict=session.createQuery(query).list();
		System.out.println("Cantidad de filas : "+listDistrict.size());
		
		session.close();
		return listDistrict;
	}
	
	public List<District> listDistrictAllLima(int idProvince) throws Exception {
		String query="from District where provinceId in(127,66)";
		System.out.println("query : "+query);
		Session session=sessionfactory.openSession();
		
		List<District> listDistrict=session.createQuery(query).list();
		System.out.println("Cantidad de filas : "+listDistrict.size());
		
		session.close();
		return listDistrict;
	}
	
}
