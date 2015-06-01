package com.rest.service.inmobile.hibernate.impl;

import java.util.List;

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
	
	public Complaint saveComplientObject(Complaint beanComplient) throws Exception {
		Session session=sessionfactory.openSession();
		Transaction transaction=session.beginTransaction();
		session.saveOrUpdate(beanComplient);
		System.out.println("Last ID : "+beanComplient.getId());
		
		transaction.commit();
		session.close();
		return beanComplient;
	}

	public List<Complaint> getComplaintByUser(int idUser)throws Exception{
		String query="from Complaint where idUser='"+idUser+"'";
		System.out.println("query : "+query);
		Session session=sessionfactory.openSession();
		
		List<Complaint> listComplaint=session.createQuery(query).list();
		System.out.println("Cantidad de filas : "+listComplaint.size());
		
		session.close();
		return listComplaint;
	}
	
	public List<Complaint> getComplaintByDistrict(String nameDistrict)throws Exception{
		String query="from Complaint where distrinctName like '%"+nameDistrict+"%'";
		System.out.println("query : "+query);
		Session session=sessionfactory.openSession();
		
		List<Complaint> listComplaint=session.createQuery(query).list();
		System.out.println("Cantidad de filas : "+listComplaint.size());
		
		session.close();
		return listComplaint;
	}
	
	public Complaint getComplaintByNumberPlate(String numberPlate)throws Exception{
		Complaint beanComplaint=new Complaint();
		String query="from Complaint where numberPlate='"+numberPlate+"'";
		System.out.println("query : "+query);
		Session session=sessionfactory.openSession();
		
		List<Complaint> listComplaint=session.createQuery(query).list();
		System.out.println("Cantidad de filas : "+listComplaint.size());
		
		session.close();
		
		if(listComplaint.size()>0){
			beanComplaint=listComplaint.get(0);
		}
		return beanComplaint;
	}
	
	public Complaint getComplaintByIdComplaint(int idCompaint)throws Exception{
		Complaint beanComplaint=new Complaint();
		String query="from Complaint where id='"+idCompaint+"'";
		System.out.println("query : "+query);
		Session session=sessionfactory.openSession();
		
		List<Complaint> listComplaint=session.createQuery(query).list();
		System.out.println("Cantidad de filas : "+listComplaint.size());
		
		session.close();
		
		if(listComplaint.size()>0){
			beanComplaint=listComplaint.get(0);
		}
		return beanComplaint;
	}
}
