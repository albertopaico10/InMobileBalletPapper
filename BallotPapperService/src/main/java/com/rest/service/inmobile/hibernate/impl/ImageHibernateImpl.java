package com.rest.service.inmobile.hibernate.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.service.inmobile.hibernate.ImageHibernate;
import com.rest.service.inmobile.hibernate.bean.Complaint;
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
	
	public Image getImage(int idImage)throws Exception{
		Image beanImage=new Image();
		String query="from Image where id='"+idImage+"'";
		System.out.println("query : "+query);
		Session session=sessionfactory.openSession();
		
		List<Image> listImage=session.createQuery(query).list();
		System.out.println("Cantidad de filas : "+listImage.size());
		
		session.close();
		
		if(listImage.size()>0){
			beanImage=listImage.get(0);
		}
		return beanImage;
	}

}
