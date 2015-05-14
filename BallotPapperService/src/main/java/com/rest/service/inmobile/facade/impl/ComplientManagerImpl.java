package com.rest.service.inmobile.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.inmobile.bean.complient.ComplientRequest;
import com.rest.service.inmobile.bean.complient.ComplientResponse;
import com.rest.service.inmobile.facade.ComplientManager;
import com.rest.service.inmobile.facade.ReqRespManager;
import com.rest.service.inmobile.hibernate.ComplientHibernate;
import com.rest.service.inmobile.hibernate.bean.Complaint;
import com.rest.service.inmobile.hibernate.bean.RequestResponse;
import com.rest.service.inmobile.util.CommonConstants;
import com.rest.service.inmobile.util.ConvertClass;

@Service
@Transactional
public class ComplientManagerImpl implements ComplientManager {

	@Autowired
	private ReqRespManager reqRespManager;
	
	private ComplientHibernate complientHibernate;
	
	public ComplientResponse saveComplient(ComplientRequest beanRequest) {
		ComplientResponse beanResponse=new ComplientResponse();
		RequestResponse valueReqResp = (RequestResponse) reqRespManager.saveOrUpdate(beanRequest,
				CommonConstants.TypeOperationReqResp.OPERATION_UPLOAD_IMAGE_MOBILE,beanRequest.getIdUser(), 0);
		System.out.println("ID Response : " + valueReqResp.getId());
		
		try {
			Complaint beanComplient=ConvertClass.convertComplientToDataBase(beanRequest);
			beanComplient.setStatus(1);
			int idComplient=complientHibernate.saveComplient(beanComplient);
			
			beanResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_SUCCESS_IMAGE);
			beanResponse.setMessageResponse("Se grabó la imagen con exito");
			beanResponse.setIdComplient(idComplient);
			
		} catch (Exception e) {
			beanResponse.setCodeResponse(CommonConstants.CodeResponse.CODE_RESPONSE_ERROR);
			beanResponse.setMessageResponse(e.getMessage());
		}
		
		reqRespManager.saveOrUpdate(beanResponse,CommonConstants.TypeOperationReqResp.OPERATION_SAVE_COMPLIENT,
				beanRequest.getIdUser(),valueReqResp.getId());
		return beanResponse;
	}

	
}
