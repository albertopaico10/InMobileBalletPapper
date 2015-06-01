package com.rest.service.inmobile.hibernate;

import com.rest.service.inmobile.hibernate.bean.Image;

public interface ImageHibernate {
	
	public int saveImageId(Image imageBean) throws Exception;
	public Image getImage(int idImage)throws Exception;
	
}
