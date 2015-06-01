package com.rest.service.inmobile.facade;

import org.springframework.stereotype.Service;

import com.rest.service.inmobile.bean.ubigeo.UbigeoResponse;
@Service
public interface UbigeoManager {
	public UbigeoResponse listAllDistrict(int idProvince);
}
