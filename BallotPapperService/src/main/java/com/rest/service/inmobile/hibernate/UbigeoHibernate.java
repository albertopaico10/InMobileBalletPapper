package com.rest.service.inmobile.hibernate;

import java.util.List;

import com.rest.service.inmobile.hibernate.bean.District;

public interface UbigeoHibernate {
	List<District> listDistrict(int idProvince) throws Exception;
	List<District> listDistrictAllLima(int idProvince) throws Exception;
}
