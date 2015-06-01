package com.rest.service.inmobile.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.inmobile.bean.ubigeo.Ubigeo;
import com.rest.service.inmobile.bean.ubigeo.UbigeoResponse;
import com.rest.service.inmobile.facade.UbigeoManager;
import com.rest.service.inmobile.hibernate.UbigeoHibernate;
import com.rest.service.inmobile.hibernate.bean.District;
import com.rest.service.inmobile.util.CommonConstants;
import com.rest.service.inmobile.util.ConvertClass;

@Service
@Transactional
public class UbigeoManagerImpl implements UbigeoManager {

	@Autowired
	private UbigeoHibernate ubigeoHibernate;
	
	public UbigeoResponse listAllDistrict(int idProvince) {
		UbigeoResponse ubigeoBeanResponse=new UbigeoResponse();
		try {
//			List<District> listDistrict=ubigeoHibernate.listDistrict(idProvince);
			List<District> listDistrict=ubigeoHibernate.listDistrictAllLima(idProvince);
			List<Ubigeo> ubigeoBean=ConvertClass.convertDataBaseDistricttoUbigeo(listDistrict);
			ubigeoBeanResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_SUCCESS_DISTRICT);
			ubigeoBeanResponse.setMessagesResponse("Retorna todos los distritos para el departamento de id "+idProvince);
			ubigeoBeanResponse.setUbigeoBean(ubigeoBean);
		} catch (Exception e) {
			ubigeoBeanResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_ERROR);
			ubigeoBeanResponse.setMessagesResponse(e.getMessage());
		}
		return ubigeoBeanResponse;
	}

}
