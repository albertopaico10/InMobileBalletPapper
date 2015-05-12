package com.rest.service.inmobile.hibernate;

import com.rest.service.inmobile.hibernate.bean.User;

public interface UserHibernate {
	public int saveUserResponseId(User userBean)throws Exception;
	public boolean existEmail(String email)throws Exception;
	public User validateUser(String email,String password)throws Exception;
}
