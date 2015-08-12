package com.rest.service.inmobile.hibernate.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.service.inmobile.hibernate.SystemParamHibernate;
import com.rest.service.inmobile.hibernate.bean.SystemParam;

@Repository
public class SystemParamHibernateImpl implements SystemParamHibernate {
	
	@Autowired
	SessionFactory sessionfactory;

	public List<SystemParam> listsSpecificSystemParam(List<String> listValues) {
		List<SystemParam> listSystemParam=new ArrayList<SystemParam>();
		
		for(String nameParam:listValues){
			SystemParam beanSystemParam=new SystemParam();
			String query="from SystemParam where nameParam='"+nameParam+"'";
			System.out.println("query : "+query);
			Session session=sessionfactory.openSession();
			List<SystemParam> listSystemParamResult=session.createQuery(query).list();
			System.out.println("Cantidad de filas : "+listSystemParamResult.size());
			session.close();
			if(listSystemParamResult.size()>0){
				beanSystemParam=listSystemParamResult.get(0);
				listSystemParam.add(beanSystemParam);
			}
		}
		return listSystemParam;
	}

	public List<SystemParam> listsByParam(String generalParam) {
		String query="from SystemParam where generalParam='"+generalParam+"'";
		System.out.println("query : "+query);
		Session session=sessionfactory.openSession();
		List<SystemParam> listSystemParamResult=session.createQuery(query).list();
		System.out.println("Cantidad de filas : "+listSystemParamResult.size());
		session.close();
		return listSystemParamResult;
	}
	
	
}
