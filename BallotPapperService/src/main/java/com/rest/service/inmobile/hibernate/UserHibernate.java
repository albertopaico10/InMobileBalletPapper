package com.rest.service.inmobile.hibernate;

import com.rest.service.inmobile.hibernate.bean.User;

public interface UserHibernate {
	public int saveUserResponseId(User userBean)throws Exception;
}
