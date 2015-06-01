package com.rest.service.inmobile.hibernate;

import java.util.List;

import com.rest.service.inmobile.hibernate.bean.ComplaintImage;

public interface ComplaintImageHibernate {

	public int saveComplientImage(ComplaintImage beanComplientImage)throws Exception;
	public List<ComplaintImage> getListImage(int idComplaint)throws Exception;
	
}
